package website.yny84666.spzx.order.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import website.yny84666.spzx.order.domain.OrderItem;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import website.yny84666.spzx.order.mapper.OrderInfoMapper;
import website.yny84666.spzx.order.domain.OrderInfo;
import website.yny84666.spzx.order.service.OrderInfoService;
import website.yny84666.spzx.order.mapper.OrderItemMapper;
/**
 * 订单Service业务层处理
 *
 * @author atguigu
 * @date 2024-10-07
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService
{
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private OrderItemMapper orderItemMapper;

    /**
     * 查询订单列表
     *
     * @param orderInfo 订单
     * @return 订单
     */
    @Override
    public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo)
    {
        return orderInfoMapper.selectOrderInfoList(orderInfo);
    }

    @Override
    public OrderInfo selectOrderInfoById(Long id) {
        OrderInfo orderInfo = orderInfoMapper.selectById(id);
        List<OrderItem> orderItemList = orderItemMapper.selectList(new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, id));
        orderInfo.setOrderItemList(orderItemList);
        return orderInfo;
    }

}
