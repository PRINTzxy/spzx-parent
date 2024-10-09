package website.yny84666.spzx.product.service;

import website.yny84666.spzx.product.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import website.yny84666.spzx.product.api.domain.vo.ProductSku;

import java.util.List;

/**
* @author Dell
* @description 针对表【product(商品)】的数据库操作Service
* @createDate 2024-09-24 14:19:26
*/
public interface ProductService extends IService<Product> {

    List<Product> selectProductList(Product product);

    int insertProduct(Product product);

    Product selectProductById(Long id);

    int updateProduct(Product product);

    int deleteProductByIds(Long[] ids);

    void updateAuditStatus(Long id, Integer auditStatus);

    void updateStatus(Long id, Integer status);

    List<ProductSku> getTopSale();
}
