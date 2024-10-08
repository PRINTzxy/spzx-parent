package website.yny84666.spzx.product.service;

import website.yny84666.spzx.product.domain.ProductSpec;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Dell
* @description 针对表【product_spec(商品规格)】的数据库操作Service
* @createDate 2024-09-24 14:19:26
*/
public interface ProductSpecService extends IService<ProductSpec> {

    List<ProductSpec> selectProductSpecList(ProductSpec productSpec);

    ProductSpec selectProductSpecById(Long id);

    List<ProductSpec> selectProductSpecListByCategoryId(Long categoryId);
}
