package website.yny84666.spzx.product.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import website.yny84666.spzx.product.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import website.yny84666.spzx.product.domain.dto.ProductDetailsDTO;
import website.yny84666.spzx.product.domain.vo.ProductDetailVO;

import java.util.List;

/**
* @author Dell
* @description 针对表【product(商品)】的数据库操作Service
* @createDate 2024-09-24 14:19:26
*/
public interface ProductService extends IService<Product> {
    List<ProductDetailVO> selectProductList(ProductDetailsDTO productDetailsDTO);

    void updateAuditStatus(Long id, Integer auditStatus);

    void updateStatus(Long id, Integer status);

    int updateProductDetailsDTO(ProductDetailsDTO productDetailsDTO);


    ProductDetailVO selectProductDetailById(Long id);
}
