package website.yny84666.spzx.user.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import website.yny84666.spzx.common.core.exception.ServiceException;
import org.springframework.stereotype.Service;
import website.yny84666.spzx.user.api.domain.UpdateUserLogin;
import website.yny84666.spzx.user.domain.UserAddress;
import website.yny84666.spzx.user.mapper.UserAddressMapper;
import website.yny84666.spzx.user.mapper.UserInfoMapper;
import website.yny84666.spzx.user.api.domain.UserInfo;
import website.yny84666.spzx.user.service.UserInfoService;

/**
 * 会员Service业务层处理
 *
 * @author atguigu
 * @date 2024-10-06
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService
{
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private UserAddressMapper userAddressMapper;

    /**
     * 查询会员列表
     *
     * @param userInfo 会员
     * @return 会员
     */
    @Override
    public List<UserInfo> selectUserInfoList(UserInfo userInfo)
    {
        return userInfoMapper.selectUserInfoList(userInfo);
    }

    @Override
    public List<UserAddress> selectUserAddressList(Long userId) {
        return userAddressMapper.selectList(new LambdaQueryWrapper<UserAddress>().eq(UserAddress::getUserId,userId));
    }

    ////////////////////////////////////////////////////////////
    @Override
    public void register(UserInfo userInfo) {
        Long count = userInfoMapper.selectCount(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUsername, userInfo.getUsername()));
        if (count>0) throw new ServiceException("手机号已存在");
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");

        userInfoMapper.insert(userInfo);
    }

    @Override
    public UserInfo selectUserByUserName(String username) {
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUsername, username));
    }

    @Override
    public Boolean updateUserLogin(UpdateUserLogin updateUserLogin) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(updateUserLogin.getUserId());
        userInfo.setLastLoginIp(updateUserLogin.getLastLoginIp());
        userInfo.setLastLoginTime(updateUserLogin.getLastLoginTime());
        userInfoMapper.updateById(userInfo);
        return true;
    }

}
