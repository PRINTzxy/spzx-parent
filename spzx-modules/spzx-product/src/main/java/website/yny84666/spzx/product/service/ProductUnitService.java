package website.yny84666.spzx.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import website.yny84666.spzx.product.domain.ProductUnit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Dell
* @description 针对表【product_unit(商品单位)】的数据库操作Service
* @createDate 2024-09-24 14:19:26
*/
public interface ProductUnitService extends IService<ProductUnit> {

    Page<ProductUnit> selectProductUnitPage(ProductUnit productUnit);

    void checkUniqueUnitName(ProductUnit productUnit);
}
