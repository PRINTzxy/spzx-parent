package website.yny84666.spzx.product.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import website.yny84666.spzx.product.domain.ProductUnit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Dell
* @description 针对表【product_unit(商品单位)】的数据库操作Mapper
* @createDate 2024-09-24 14:19:26
* @Entity website.yny84666.spzx.product.domain.ProductUnit
*/
public interface ProductUnitMapper extends BaseMapper<ProductUnit> {

    IPage<ProductUnit> selectProductUnitPage(Page<ProductUnit> pageParam,@Param("query") ProductUnit productUnit);
}




