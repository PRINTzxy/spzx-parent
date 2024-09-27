package website.yny84666.spzx.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import website.yny84666.spzx.product.domain.Brand;
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

    List<CategoryBrand> selectCategoryBrandList(CategoryBrand categoryBrand);

    CategoryBrand selectCategoryBrandById(Long id);

    int insertCategoryBrand(CategoryBrand categoryBrand);

    int updateCategoryBrand(CategoryBrand categoryBrand);

    List<Brand> selectBrandListByCategoryId(Long categoryId);
}
