package website.yny84666.spzx.product.mapper;

import website.yny84666.spzx.product.api.domain.vo.Brand;
import website.yny84666.spzx.product.domain.CategoryBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Dell
* @description 针对表【category_brand(分类品牌)】的数据库操作Mapper
* @createDate 2024-09-24 14:19:26
* @Entity website.yny84666.spzx.product.domain.CategoryBrand
*/
public interface CategoryBrandMapper extends BaseMapper<CategoryBrand> {

    List<CategoryBrand> selectCategoryBrandList(CategoryBrand categoryBrand);

    List<Brand> selectBrandListByCategoryId(Long categoryId);
}




