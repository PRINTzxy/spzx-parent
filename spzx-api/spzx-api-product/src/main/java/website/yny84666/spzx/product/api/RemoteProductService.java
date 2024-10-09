package website.yny84666.spzx.product.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import website.yny84666.spzx.common.core.constant.SecurityConstants;
import website.yny84666.spzx.common.core.constant.ServiceNameConstants;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.product.api.domain.vo.ProductSku;
import website.yny84666.spzx.product.api.factory.RemoteProductFallbackFactory;

import java.util.List;

@FeignClient(contextId = "RemoteProductService", value = ServiceNameConstants.PRODUCT_SERVICE, fallbackFactory = RemoteProductFallbackFactory.class)
public interface RemoteProductService {
    @GetMapping("/product/getTopSale")
    public R<List<ProductSku>> getTopSale(@RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
