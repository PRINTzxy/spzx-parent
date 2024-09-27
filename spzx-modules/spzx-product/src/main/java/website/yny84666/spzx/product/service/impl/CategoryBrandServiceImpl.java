package website.yny84666.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import website.yny84666.spzx.common.core.exception.ServiceException;
import website.yny84666.spzx.common.core.web.page.PageDomain;
import website.yny84666.spzx.common.core.web.page.TableSupport;
import website.yny84666.spzx.product.domain.Brand;
import website.yny84666.spzx.product.domain.CategoryBrand;
import website.yny84666.spzx.product.domain.ProductUnit;
import website.yny84666.spzx.product.service.CategoryBrandService;
import website.yny84666.spzx.product.mapper.CategoryBrandMapper;
import org.springframework.stereotype.Service;
import website.yny84666.spzx.product.service.CategoryService;

import java.util.List;

/**
* @author Dell
* @description 针对表【category_brand(分类品牌)】的数据库操作Service实现
* @createDate 2024-09-24 14:19:26
*/
@Service
public class CategoryBrandServiceImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrand>
    implements CategoryBrandService{

    @Resource
    private CategoryBrandMapper categoryBrandMapper;
    @Resource
    private CategoryService categoryService;


    @Override
    public List<CategoryBrand> selectCategoryBrandList(CategoryBrand categoryBrand) {
        return categoryBrandMapper.selectCategoryBrandList(categoryBrand);
    }

    @Override
    public CategoryBrand selectCategoryBrandById(Long id) {
        CategoryBrand categoryBrand = this.categoryBrandMapper.selectById(id);
        List<Long> categoryIdList = categoryService.getAllCategoryIdList(categoryBrand.getCategoryId());
        categoryBrand.setCategoryIdList(categoryIdList);
        return categoryBrand;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertCategoryBrand(CategoryBrand categoryBrand) {
        long count = categoryBrandMapper.selectCount(new LambdaQueryWrapper<CategoryBrand>()
                .eq(CategoryBrand::getCategoryId,categoryBrand.getCategoryId())
                .eq(CategoryBrand::getBrandId,categoryBrand.getBrandId()));
        if(count>0) throw new ServiceException("该分类已添加该品牌");
        return categoryBrandMapper.insert(categoryBrand);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateCategoryBrand(CategoryBrand categoryBrand) {
        CategoryBrand originalCategoryBrand = this.getById(categoryBrand.getId());
        if(categoryBrand.getCategoryId().longValue() != originalCategoryBrand.getCategoryId().longValue() || categoryBrand.getBrandId().longValue() != originalCategoryBrand.getBrandId().longValue()){
            long count = categoryBrandMapper.selectCount(new LambdaQueryWrapper<CategoryBrand>()
                    .eq(CategoryBrand::getCategoryId,categoryBrand.getCategoryId())
                    .eq(CategoryBrand::getBrandId,categoryBrand.getBrandId()));
            if(count>0) throw new ServiceException("该分类已添加该品牌");
        }
        return categoryBrandMapper.updateById(categoryBrand);
    }

    @Override
    public List<Brand> selectBrandListByCategoryId(Long categoryId) {
        return categoryBrandMapper.selectBrandListByCategoryId(categoryId);
    }
}




