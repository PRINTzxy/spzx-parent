package website.yny84666.spzx.product.mapper;

import website.yny84666.spzx.product.domain.Brand;

import java.util.List;

public interface BrandMapper {
    List<Brand> selectBrandList(Brand brand);
}
