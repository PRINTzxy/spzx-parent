package website.yny84666.spzx.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import website.yny84666.spzx.user.domain.OrderLog;
import website.yny84666.spzx.user.service.OrderLogService;
import website.yny84666.spzx.user.mapper.OrderLogMapper;
import org.springframework.stereotype.Service;

/**
* @author Dell
* @description 针对表【order_log(订单操作日志记录)】的数据库操作Service实现
* @createDate 2024-10-07 02:27:48
*/
@Service
public class OrderLogServiceImpl extends ServiceImpl<OrderLogMapper, OrderLog>
    implements OrderLogService{

}




