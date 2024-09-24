package website.yny84666.spzx.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import website.yny84666.spzx.product.domain.Product;
import website.yny84666.spzx.product.service.ProductService;
import website.yny84666.spzx.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
* @author Dell
* @description 针对表【product(商品)】的数据库操作Service实现
* @createDate 2024-09-24 14:19:26
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

}




