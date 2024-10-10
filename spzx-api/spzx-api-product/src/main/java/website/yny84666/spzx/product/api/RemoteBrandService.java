package website.yny84666.spzx.product.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import website.yny84666.spzx.common.core.constant.SecurityConstants;
import website.yny84666.spzx.common.core.constant.ServiceNameConstants;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.product.api.domain.vo.Brand;
import website.yny84666.spzx.product.api.factory.RemoteBrandFallbackFactory;

import java.util.List;

@FeignClient(contextId = "remoteBrandService", value = ServiceNameConstants.PRODUCT_SERVICE, fallbackFactory = RemoteBrandFallbackFactory.class)
public interface RemoteBrandService {
    @GetMapping("/brand/getBrandAllList")
    public R<List<Brand>> getBrandAllList(@RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
