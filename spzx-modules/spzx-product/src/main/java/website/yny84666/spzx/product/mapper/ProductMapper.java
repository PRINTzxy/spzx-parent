package website.yny84666.spzx.product.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import website.yny84666.spzx.product.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import website.yny84666.spzx.product.domain.dto.ProductDetailsDTO;
import website.yny84666.spzx.product.domain.vo.ProductDetailVO;

import java.util.List;

/**
* @author Dell
* @description 针对表【product(商品)】的数据库操作Mapper
* @createDate 2024-09-24 14:19:26
* @Entity website.yny84666.spzx.product.domain.Product
*/
public interface ProductMapper extends BaseMapper<Product> {

    List<ProductDetailVO> selectProductList(ProductDetailsDTO productDetailsDTO);

}




