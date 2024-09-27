package website.yny84666.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import website.yny84666.spzx.common.core.exception.ServiceException;
import website.yny84666.spzx.product.domain.ProductSpec;
import website.yny84666.spzx.product.service.CategoryService;
import website.yny84666.spzx.product.service.ProductSpecService;
import website.yny84666.spzx.product.mapper.ProductSpecMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Dell
* @description 针对表【product_spec(商品规格)】的数据库操作Service实现
* @createDate 2024-09-24 14:19:26
*/
@Service
public class ProductSpecServiceImpl extends ServiceImpl<ProductSpecMapper, ProductSpec>
    implements ProductSpecService{

    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductSpecMapper productSpecMapper;

    @Override
    public List<ProductSpec> selectProductSpecList(ProductSpec productSpec) {
        return productSpecMapper.selectProductSpecList(productSpec);
    }

    @Override
    public ProductSpec selectProductSpecById(Long id) {
        ProductSpec productSpec = productSpecMapper.selectById(id);
        List<Long> categoryIdList = categoryService.getAllCategoryIdList(productSpec.getCategoryId());
        productSpec.setCategoryIdList(categoryIdList);
        return productSpec;
    }

    @Override
    public List<ProductSpec> selectProductSpecListByCategoryId(Long categoryId) {
        return productSpecMapper.selectList(new LambdaQueryWrapper<ProductSpec>().eq(ProductSpec::getCategoryId,categoryId));
    }
}




