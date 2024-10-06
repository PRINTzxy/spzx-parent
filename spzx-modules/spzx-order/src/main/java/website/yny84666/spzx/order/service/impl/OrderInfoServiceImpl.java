package website.yny84666.spzx.order.service.impl;

import java.util.List;
import java.util.Arrays;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import website.yny84666.spzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.yny84666.spzx.order.mapper.OrderInfoMapper;
import website.yny84666.spzx.order.domain.OrderInfo;
import website.yny84666.spzx.order.service.OrderInfoService;

/**
 * 订单Service业务层处理
 *
 * @author atguigu
 * @date 2024-10-07
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService
{
    @Autowired
    private OrderInfoMapper orderInfoMapper;

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

}
