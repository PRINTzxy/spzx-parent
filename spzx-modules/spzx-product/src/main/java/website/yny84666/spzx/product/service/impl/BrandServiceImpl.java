package website.yny84666.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import website.yny84666.spzx.common.core.exception.ServiceException;
import website.yny84666.spzx.common.security.utils.SecurityUtils;
import website.yny84666.spzx.product.domain.Brand;
import website.yny84666.spzx.product.service.BrandService;
import website.yny84666.spzx.product.mapper.BrandMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Dell
* @description 针对表【brand(分类品牌)】的数据库操作Service实现
* @createDate 2024-09-24 14:57:18
*/
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand>
    implements BrandService{

    @Resource
    private BrandMapper brandMapper;


    @Override
    public List<Brand> selectBrandList(Brand brand) {
        return brandMapper.selectBrandList(brand);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateBrand(Brand brand) {
        return brandMapper.updateBrand(brand);
    }
}




