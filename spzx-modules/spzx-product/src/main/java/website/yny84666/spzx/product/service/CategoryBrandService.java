package website.yny84666.spzx.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import website.yny84666.spzx.product.domain.CategoryBrand;
import com.baomidou.mybatisplus.extension.service.IService;
import website.yny84666.spzx.product.domain.ProductUnit;

import java.util.List;

/**
* @author Dell
* @description 针对表【category_brand(分类品牌)】的数据库操作Service
* @createDate 2024-09-24 14:19:26
*/
public interface CategoryBrandService extends IService<CategoryBrand> {

    void checkUniqueCategoryBrand(CategoryBrand categoryBrand);

    List<CategoryBrand> selectCategoyBrandVOList(CategoryBrand categoryBrand);
}
