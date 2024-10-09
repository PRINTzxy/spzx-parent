package website.yny84666.spzx.product.mapper;

import website.yny84666.spzx.product.api.domain.vo.ProductSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Dell
* @description 针对表【product_sku(商品sku)】的数据库操作Mapper
* @createDate 2024-09-24 14:19:26
* @Entity website.yny84666.spzx.product.api.domain.vo.ProductSku
*/
public interface ProductSkuMapper extends BaseMapper<ProductSku> {

    List<ProductSku> getTopSale();
}




