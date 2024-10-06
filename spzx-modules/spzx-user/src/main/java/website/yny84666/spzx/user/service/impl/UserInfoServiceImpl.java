package website.yny84666.spzx.user.service.impl;

import java.util.List;
import java.util.Arrays;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import website.yny84666.spzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.yny84666.spzx.user.domain.UserAddress;
import website.yny84666.spzx.user.mapper.UserAddressMapper;
import website.yny84666.spzx.user.mapper.UserInfoMapper;
import website.yny84666.spzx.user.domain.UserInfo;
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

}
