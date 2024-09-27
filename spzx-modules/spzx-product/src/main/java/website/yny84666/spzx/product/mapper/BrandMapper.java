package website.yny84666.spzx.product.mapper;

import website.yny84666.spzx.product.domain.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Dell
* @description 针对表【brand(分类品牌)】的数据库操作Mapper
* @createDate 2024-09-24 14:57:18
* @Entity website.yny84666.spzx.product.domain.Brand
*/
public interface BrandMapper extends BaseMapper<Brand> {


    List<Brand> selectBrandList(Brand brand);

    int updateBrand(Brand brand);
}




