package website.yny84666.spzx.order.mapper;

import java.util.List;
import website.yny84666.spzx.order.domain.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 订单项信息Mapper接口
 *
 * @author atguigu
 * @date 2024-10-07
 */
public interface OrderItemMapper extends BaseMapper<OrderItem>
{

    /**
     * 查询订单项信息列表
     *
     * @param orderItem 订单项信息
     * @return 订单项信息集合
     */
    public List<OrderItem> selectOrderItemList(OrderItem orderItem);

}
