package website.yny84666.spzx.user.service.impl;

import java.util.List;
import java.util.Arrays;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import website.yny84666.spzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.yny84666.spzx.user.mapper.UserAddressMapper;
import website.yny84666.spzx.user.domain.UserAddress;
import website.yny84666.spzx.user.service.UserAddressService;

/**
 * 用户地址Service业务层处理
 *
 * @author atguigu
 * @date 2024-10-06
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService
{
    @Resource
    private UserAddressMapper userAddressMapper;

    /**
     * 查询用户地址列表
     *
     * @param userAddress 用户地址
     * @return 用户地址
     */
    @Override
    public List<UserAddress> selectUserAddressList(UserAddress userAddress)
    {
        return userAddressMapper.selectUserAddressList(userAddress);
    }

}
