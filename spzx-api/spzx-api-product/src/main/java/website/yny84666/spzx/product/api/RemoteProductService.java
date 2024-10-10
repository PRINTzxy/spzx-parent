package website.yny84666.spzx.product.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import website.yny84666.spzx.common.core.constant.SecurityConstants;
import website.yny84666.spzx.common.core.constant.ServiceNameConstants;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.product.api.domain.vo.*;
import website.yny84666.spzx.product.api.factory.RemoteProductFallbackFactory;

import java.util.List;
import java.util.Map;

@FeignClient(contextId = "RemoteProductService", value = ServiceNameConstants.PRODUCT_SERVICE, fallbackFactory = RemoteProductFallbackFactory.class)
public interface RemoteProductService {
    @GetMapping("/product/getTopSale")
    public R<List<ProductSku>> getTopSale(@RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping("/product/skuList/{pageNum}/{pageSize}")
    public R<TableDataInfo> skuList(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @SpringQueryMap SkuQuery skuQuery, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    ///////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/product/getProductSku/{skuId}")
    public R<ProductSku> getProductSku(@PathVariable("skuId") Long skuId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping(value = "/product/getProduct/{id}")
    public R<Product> getProduct(@PathVariable("id") Long id, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping(value = "/product/getSkuPrice/{skuId}")
    public R<SkuPrice> getSkuPrice(@PathVariable("skuId") Long skuId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping(value = "/product/getProductDetails/{id}")
    public R<ProductDetails> getProductDetails(@PathVariable("id") Long id, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping(value = "/product/getSkuSpecValue/{id}")
    public R<Map<String, Long>> getSkuSpecValue(@PathVariable("id") Long id, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping(value = "/product/getSkuStock/{skuId}")
    public R<SkuStockVo> getSkuStock(@PathVariable("skuId") Long skuId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
