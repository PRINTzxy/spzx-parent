package website.yny84666.spzx.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import website.yny84666.spzx.user.domain.OrderItem;
import website.yny84666.spzx.user.service.OrderItemService;
import website.yny84666.spzx.user.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author Dell
* @description 针对表【order_item(订单项信息)】的数据库操作Service实现
* @createDate 2024-10-07 02:27:48
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService{

}




