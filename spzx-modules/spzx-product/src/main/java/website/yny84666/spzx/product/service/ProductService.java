package website.yny84666.spzx.product.service;

import website.yny84666.spzx.product.api.domain.vo.SkuPrice;
import website.yny84666.spzx.product.api.domain.vo.SkuQuery;
import website.yny84666.spzx.product.api.domain.vo.SkuStockVo;
import website.yny84666.spzx.product.api.domain.vo.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import website.yny84666.spzx.product.api.domain.vo.ProductSku;
import website.yny84666.spzx.product.api.domain.vo.ProductDetails;

import java.util.List;
import java.util.Map;

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

    List<ProductSku> selectProductSkuList(SkuQuery skuQuery);

    ////////////////////////////////////////////////////////////////////////////////
    ProductSku getProductSku(Long skuId);

    Product getProduct(Long id);

    SkuPrice getSkuPrice(Long skuId);

    ProductDetails getProductDetails(Long id);

    Map<String, Long> getSkuSpecValue(Long id);

    SkuStockVo getSkuStock(Long skuId);
}
