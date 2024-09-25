package website.yny84666.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import website.yny84666.spzx.common.core.exception.ServiceException;
import website.yny84666.spzx.common.core.web.page.PageDomain;
import website.yny84666.spzx.common.core.web.page.TableSupport;
import website.yny84666.spzx.product.domain.CategoryBrand;
import website.yny84666.spzx.product.domain.ProductUnit;
import website.yny84666.spzx.product.service.CategoryBrandService;
import website.yny84666.spzx.product.mapper.CategoryBrandMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Dell
* @description 针对表【category_brand(分类品牌)】的数据库操作Service实现
* @createDate 2024-09-24 14:19:26
*/
@Service
public class CategoryBrandServiceImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrand>
    implements CategoryBrandService{

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public void checkUniqueCategoryBrand(CategoryBrand categoryBrand) {
        Long id = categoryBrand.getId()==null?-1L:categoryBrand.getId();
        CategoryBrand categoryBrand1 = baseMapper.selectOne(Wrappers.lambdaQuery(CategoryBrand.class).eq(CategoryBrand::getCategoryId,categoryBrand.getCategoryId()).eq(CategoryBrand::getBrandId,categoryBrand.getBrandId()).last("limit 1"));
        if(categoryBrand1!=null && !id.equals(categoryBrand1.getId())){
            throw new ServiceException("分类品牌已存在");
        }

    }

    @Override
    public List<CategoryBrand> selectCategoyBrandVOList(CategoryBrand categoryBrand) {
        return categoryBrandMapper.selectCategoyBrandVOList(categoryBrand);
    }


}




