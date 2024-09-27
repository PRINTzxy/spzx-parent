package website.yny84666.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
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

    @Resource
    private ProductUnitMapper productUnitMapper;

    @Override
    public IPage<ProductUnit> selectProductUnitPage(Page<ProductUnit> pageParam, ProductUnit productUnit) {
        return productUnitMapper.selectProductUnitPage(pageParam,productUnit);
    }

    /*@Override
    public IPage<ProductUnit> selectProductUnitPage(Page<ProductUnit> pageParam, ProductUnit productUnit) {
        QueryWrapper<ProductUnit> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(productUnit.getName())){
            wrapper.like("name",productUnit.getName());
        }
        Page<ProductUnit> productUnitPage = baseMapper.selectPage(pageParam, wrapper);
        return productUnitPage;
    }*/
}




