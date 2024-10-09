package website.yny84666.spzx.product.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import website.yny84666.spzx.common.core.constant.SecurityConstants;
import website.yny84666.spzx.common.core.constant.ServiceNameConstants;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.product.api.domain.vo.CategoryVo;
import website.yny84666.spzx.product.api.factory.RemoteCategoryFallbackFactory;

import java.util.List;

@FeignClient(contextId = "remoteCategoryService", value = ServiceNameConstants.PRODUCT_SERVICE, fallbackFactory = RemoteCategoryFallbackFactory.class)
public interface RemoteCategoryService {
    @GetMapping(value = "/category/getOneCategory")
    public R<List<CategoryVo>> getOneCategory(@RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping(value = "/category/tree")
    public R<List<CategoryVo>> tree(@RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
