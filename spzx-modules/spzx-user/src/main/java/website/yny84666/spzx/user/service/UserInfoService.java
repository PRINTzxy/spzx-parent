package website.yny84666.spzx.user.service;

import java.util.List;

import website.yny84666.spzx.user.api.domain.UpdateUserLogin;
import website.yny84666.spzx.user.domain.UserAddress;
import website.yny84666.spzx.user.api.domain.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 会员Service接口
 *
 * @author atguigu
 * @date 2024-10-06
 */
public interface UserInfoService extends IService<UserInfo>
{

    /**
     * 查询会员列表
     *
     * @param userInfo 会员
     * @return 会员集合
     */
    public List<UserInfo> selectUserInfoList(UserInfo userInfo);

    List<UserAddress> selectUserAddressList(Long userId);

    ///////////////////////////////////////////////////////////////

    void register(UserInfo userInfo);

    UserInfo selectUserByUserName(String username);

    Boolean updateUserLogin(UpdateUserLogin updateUserLogin);
}
