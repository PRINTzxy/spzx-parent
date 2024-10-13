package website.yny84666.spzx.auth.service;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import website.yny84666.spzx.auth.form.RegisterBody;
import website.yny84666.spzx.common.core.constant.CacheConstants;
import website.yny84666.spzx.common.core.constant.Constants;
import website.yny84666.spzx.common.core.constant.SecurityConstants;
import website.yny84666.spzx.common.core.constant.UserConstants;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.common.core.enums.UserStatus;
import website.yny84666.spzx.common.core.exception.ServiceException;
import website.yny84666.spzx.common.core.text.Convert;
import website.yny84666.spzx.common.core.utils.StringUtils;
import website.yny84666.spzx.common.core.utils.ip.IpUtils;
import website.yny84666.spzx.common.redis.service.RedisService;
import website.yny84666.spzx.common.security.utils.SecurityUtils;
import website.yny84666.spzx.system.api.model.LoginUser;
import website.yny84666.spzx.user.api.RemoteUserInfoService;
import website.yny84666.spzx.user.api.domain.UpdateUserLogin;
import website.yny84666.spzx.user.api.domain.UserInfo;

import java.lang.ref.PhantomReference;
import java.util.Date;

@Component
public class H5LoginService {

    @Resource
    private RemoteUserInfoService remoteUserInfoService;
    @Resource
    private SysRecordLogService sysRecordLogService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private SysPasswordService passwordService;
    @Resource
    private RedisService redisService;

    //注册
    public void register(RegisterBody registerBody) {

        String username = registerBody.getUsername();
        String password = registerBody.getPassword();
        String code = registerBody.getCode();
        String nickName = registerBody.getNickName();
        //校验
        if(StringUtils.isAnyBlank(username,password)) throw new ServiceException("用户名/密码必须填写");
        if(username.length() != 11) throw new ServiceException("用户名长度必须为11位");
        if(password.length() < UserConstants.PASSWORD_MIN_LENGTH || password.length() > UserConstants.PASSWORD_MAX_LENGTH) throw new ServiceException("密码长度必须在5到20个字符之间");
        if (StringUtils.isEmpty(code)) throw new ServiceException("验证码必须填写");
        String codeValue = stringRedisTemplate.opsForValue().get("phone:code:"+username);
        if (!registerBody.getCode().equals(codeValue)) throw new ServiceException("验证码不正确");
        //注册用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        nickName = StringUtils.isEmpty(nickName)?username:nickName;
        userInfo.setNickName(nickName);
        userInfo.setPassword(SecurityUtils.encryptPassword(password));
        R<?> registerResult = remoteUserInfoService.register(userInfo, SecurityConstants.INNER);
        if (R.FAIL == registerResult.getCode()) throw new ServiceException(registerResult.getMsg());
        sysRecordLogService.recordLogininfor(username, Constants.REGISTER,"注册成功");

    }

    public LoginUser login(String username, String password) {
        if (StringUtils.isAnyBlank(username, password)) {
            sysRecordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
            throw new ServiceException("用户/密码必须填写");
        }
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            sysRecordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户密码不在指定范围");
            throw new ServiceException("用户密码不在指定范围");
        }
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            sysRecordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户名不在指定范围");
            throw new ServiceException("用户名不在指定范围");
        }
        //IP黑名单校验
        String blackStr = Convert.toStr(redisService.getCacheObject(CacheConstants.SYS_LOGIN_BLACKIPLIST));
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr()))
        {
            sysRecordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "很遗憾，访问IP已被列入系统黑名单");
            throw new ServiceException("很遗憾，访问IP已被列入系统黑名单");
        }
        //查询用户信息
        R<UserInfo> userResult = remoteUserInfoService.getUserInfo(username,SecurityConstants.INNER);

        if (StringUtils.isNull(userResult)||StringUtils.isNull(userResult.getData())){
            sysRecordLogService.recordLogininfor(username,Constants.LOGIN_FAIL,"登录用户不存在");
            throw new ServiceException("登录用户：" + username + " 不存在");
        }

        if (R.FAIL == userResult.getCode()) throw new ServiceException(userResult.getMsg());

        UserInfo userInfo = userResult.getData();
        LoginUser loginUser = new LoginUser();
        loginUser.setUserid(userInfo.getId());
        loginUser.setUsername(userInfo.getUsername());
        loginUser.setPassword(userInfo.getPassword());
        loginUser.setStatus(userInfo.getStatus()+"");

        if (UserStatus.DISABLE.getCode().equals(userInfo.getStatus())) {
            sysRecordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户已停用，请联系管理员");
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }
        passwordService.validate(loginUser, password);
        sysRecordLogService.recordLogininfor(username, Constants.LOGIN_SUCCESS, "登录成功");

        //更新登录信息
        UpdateUserLogin updateUserLogin = new UpdateUserLogin();
        updateUserLogin.setUserId(userInfo.getId());
        updateUserLogin.setLastLoginIp(IpUtils.getIpAddr());
        updateUserLogin.setLastLoginTime(new Date());
        remoteUserInfoService.updateUserLogin(updateUserLogin, SecurityConstants.INNER);
        return loginUser;

    }
}
