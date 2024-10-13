package website.yny84666.spzx.user.mapper;

import java.util.List;
import website.yny84666.spzx.user.api.domain.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 会员Mapper接口
 *
 * @author atguigu
 * @date 2024-10-06
 */
public interface UserInfoMapper extends BaseMapper<UserInfo>
{

    /**
     * 查询会员列表
     *
     * @param userInfo 会员
     * @return 会员集合
     */
    public List<UserInfo> selectUserInfoList(UserInfo userInfo);

}
