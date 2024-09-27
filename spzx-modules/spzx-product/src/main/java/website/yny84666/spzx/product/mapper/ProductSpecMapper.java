package website.yny84666.spzx.product.mapper;

import website.yny84666.spzx.product.domain.ProductSpec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Dell
* @description 针对表【product_spec(商品规格)】的数据库操作Mapper
* @createDate 2024-09-24 14:19:26
* @Entity website.yny84666.spzx.product.domain.ProductSpec
*/
public interface ProductSpecMapper extends BaseMapper<ProductSpec> {


    List<ProductSpec> selectProductSpecList(ProductSpec productSpec);
}




