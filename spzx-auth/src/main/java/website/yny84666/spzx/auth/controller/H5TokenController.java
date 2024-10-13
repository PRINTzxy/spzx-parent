package website.yny84666.spzx.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import website.yny84666.spzx.auth.form.LoginBody;
import website.yny84666.spzx.auth.form.RegisterBody;
import website.yny84666.spzx.auth.service.H5LoginService;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.common.core.utils.JwtUtils;
import website.yny84666.spzx.common.core.utils.StringUtils;
import website.yny84666.spzx.common.security.auth.AuthUtil;
import website.yny84666.spzx.common.security.service.TokenService;
import website.yny84666.spzx.common.security.utils.SecurityUtils;
import website.yny84666.spzx.system.api.model.LoginUser;

@RestController
public class H5TokenController {
    @Resource
    private H5LoginService h5LoginService;
    @Resource
    private TokenService tokenService;

    @Operation(summary = "用户注册")
    @PostMapping("/h5/register")
    public R<?> register(@RequestBody RegisterBody registerBody) {
        //用户注册
        h5LoginService.register(registerBody);
        return R.ok();
    }

    @Operation(summary = "用户登录")
    @PostMapping("/h5/login")
    public R<?> login(@RequestBody LoginBody form) {
        //用户登录
        LoginUser userInfo = h5LoginService.login(form.getUsername(),form.getPassword());
        //获取登录Token
        return R.ok(tokenService.createToken(userInfo));
    }

    @Operation(summary = "用户退出")
    @DeleteMapping("/h5/logout")
    public R<?> logout(HttpServletRequest request)
    {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            //sysLoginService.logout(username);
        }
        return R.ok();
    }

}
