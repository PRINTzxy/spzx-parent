package website.yny84666.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> selectBrandList(Brand brand) {
        return baseMapper.selectBrandList(brand);
    }

    @Override
    public Brand getBrandById(Long id) {
        return baseMapper.getBrandById(id);
    }

    @Override
    public int updateBrandById(Brand brand) {
        checkBrandNameUnique(brand);
        brand.setUpdateBy(SecurityUtils.getUsername());
        return brandMapper.updateBrandById(brand);
    }

    @Override
    public int saveBrand(Brand brand) {
        checkBrandNameUnique(brand);
        brand.setCreateBy(SecurityUtils.getUsername()); //以后前端登录后才可以获取到用户名称
        return baseMapper.insert(brand);
    }

    @Override
    public int deleteBrand(List<Long> ids) {
        //判断该集合是否为空
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return brandMapper.deleteBrand(ids);
    }

    private void checkBrandNameUnique(Brand brand){
        Long id = brand.getId()==null?-1L:brand.getId(); //-1表示新增，-1是数据库中一定不存在的一个id值

        Brand dbBrand = brandMapper.selectBrandByName(brand.getName());

        if(dbBrand != null && !id.equals(dbBrand.getId())){
            throw new ServiceException("品牌已存在");
        }


    }

}




