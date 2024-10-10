package website.yny84666.spzx.product.mapper;

import website.yny84666.spzx.product.api.domain.vo.ProductSku;
import website.yny84666.spzx.product.api.domain.vo.SkuQuery;
import website.yny84666.spzx.product.api.domain.vo.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Dell
* @description 针对表【product(商品)】的数据库操作Mapper
* @createDate 2024-09-24 14:19:26
* @Entity website.yny84666.spzx.product.api.domain.vo.Product
*/
public interface ProductMapper extends BaseMapper<Product> {


    List<Product> selectProductList(Product product);

    List<ProductSku> selectProductSkuList(SkuQuery skuQuery);
}




