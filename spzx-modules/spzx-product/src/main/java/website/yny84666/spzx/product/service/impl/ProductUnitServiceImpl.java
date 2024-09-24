package website.yny84666.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import website.yny84666.spzx.common.core.exception.ServiceException;
import website.yny84666.spzx.common.core.utils.StringUtils;
import website.yny84666.spzx.common.core.web.page.PageDomain;
import website.yny84666.spzx.common.core.web.page.TableSupport;
import website.yny84666.spzx.product.domain.ProductUnit;
import website.yny84666.spzx.product.service.ProductUnitService;
import website.yny84666.spzx.product.mapper.ProductUnitMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Dell
* @description 针对表【product_unit(商品单位)】的数据库操作Service实现
* @createDate 2024-09-24 14:19:26
*/
@Service
public class ProductUnitServiceImpl extends ServiceImpl<ProductUnitMapper, ProductUnit>
    implements ProductUnitService{

    @Override
    public Page<ProductUnit> selectProductUnitPage(ProductUnit productUnit) {
        //1.通过工具获得PageDomain TableSupport
        PageDomain pageDomain = TableSupport.getPageDomain();
        //2.封装 mp需要的page分页数据
        Page<ProductUnit> page = new Page<>(pageDomain.getPageNum(),pageDomain.getPageSize());
        //利用Wrappers封装的方法lambdaQuery来使用动态sql
        LambdaQueryWrapper<ProductUnit> productUnitLambdaQueryWrapper = Wrappers.lambdaQuery(ProductUnit.class);

        //如果productUnit.name不为NULL设置 动态sql条件
        productUnitLambdaQueryWrapper.like(productUnit.getName() != null
                , ProductUnit::getName
                , productUnit.getName());



        Page<ProductUnit> productUnitPage = baseMapper.selectPage(page, productUnitLambdaQueryWrapper);
        return productUnitPage;
    }

    @Override
    public void checkUniqueUnitName(ProductUnit productUnit) {
        Long id = productUnit.getId()==null?-1L:productUnit.getId();
        ProductUnit dbUnit = baseMapper.selectOne(Wrappers.lambdaQuery(ProductUnit.class)
                .eq(ProductUnit::getName, productUnit.getName())
                .last("limit 1"));
        if(dbUnit!=null && !id.equals(dbUnit.getId())){
            throw new ServiceException("商品单位名称已存在");
        }
    }

}




