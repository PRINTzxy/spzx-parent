package website.yny84666.spzx.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.yny84666.spzx.product.domain.Brand;
import website.yny84666.spzx.product.mapper.BrandMapper;
import website.yny84666.spzx.product.service.BrandService;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public List<Brand> selectBrandList(Brand brand) {
        return brandMapper.selectBrandList(brand);
    }
}
