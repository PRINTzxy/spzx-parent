package website.yny84666.spzx.product.service;

import website.yny84666.spzx.product.api.domain.vo.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Dell
* @description 针对表【brand(分类品牌)】的数据库操作Service
* @createDate 2024-09-24 14:57:18
*/
public interface BrandService extends IService<Brand> {

    List<Brand> selectBrandList(Brand brand);

    int updateBrand(Brand brand);

}
