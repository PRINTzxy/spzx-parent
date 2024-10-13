package website.yny84666.spzx.user.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import website.yny84666.spzx.common.core.constant.SecurityConstants;
import website.yny84666.spzx.common.core.constant.ServiceNameConstants;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.user.api.domain.UpdateUserLogin;
import website.yny84666.spzx.user.api.domain.UserInfo;
import website.yny84666.spzx.user.api.factory.RemoteUserInfoFallbackFactory;

@FeignClient(contextId = "remoteUserInfoService", value = ServiceNameConstants.USER_SERVICE, fallbackFactory = RemoteUserInfoFallbackFactory.class)
public interface RemoteUserInfoService {
    @PostMapping("/userInfo/register")
    public R<Boolean> register(@RequestBody UserInfo userInfo, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping("/userInfo/info/{username}")
    public R<UserInfo> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @PutMapping("/userInfo/updateUserLogin")
    public R<Boolean> updateUserLogin(@RequestBody UpdateUserLogin updateUserLogin, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
