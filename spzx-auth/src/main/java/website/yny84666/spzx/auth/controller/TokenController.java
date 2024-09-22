package website.yny84666.spzx.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import website.yny84666.spzx.auth.form.LoginBody;
import website.yny84666.spzx.auth.form.RegisterBody;
import website.yny84666.spzx.auth.service.SysLoginService;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.common.core.utils.JwtUtils;
import website.yny84666.spzx.common.core.utils.StringUtils;
import website.yny84666.spzx.common.security.auth.AuthUtil;
import website.yny84666.spzx.common.security.service.TokenService;
import website.yny84666.spzx.common.security.utils.SecurityUtils;
import website.yny84666.spzx.system.api.model.LoginUser;

/**
 * token 控制
 * 
 * @author spzx
 */
@RestController
public class TokenController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form)
    {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request)
    {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody)
    {
        // 用户注册
        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        return R.ok();
    }
}
