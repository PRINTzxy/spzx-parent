package website.yny84666.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import website.yny84666.spzx.common.core.exception.ServiceException;
import website.yny84666.spzx.product.domain.ProductSpec;
import website.yny84666.spzx.product.service.ProductSpecService;
import website.yny84666.spzx.product.mapper.ProductSpecMapper;
import org.springframework.stereotype.Service;

/**
* @author Dell
* @description 针对表【product_spec(商品规格)】的数据库操作Service实现
* @createDate 2024-09-24 14:19:26
*/
@Service
public class ProductSpecServiceImpl extends ServiceImpl<ProductSpecMapper, ProductSpec>
    implements ProductSpecService{

    @Autowired
    private ProductSpecMapper productSpecMapper;


    @Override
    public void checkProductSpecUnique(ProductSpec productSpec) {
        Long id = productSpec.getId() == null ? -1L : productSpec.getId();
        ProductSpec productSpec1 = baseMapper.selectOne(Wrappers.lambdaQuery(ProductSpec.class).eq(ProductSpec::getSpecName, productSpec.getSpecName()).last("limit 1"));
        if (productSpec1 != null && !id.equals(productSpec1.getId())) {
            throw new ServiceException("商品单位名称已存在");
        }
    }
}




