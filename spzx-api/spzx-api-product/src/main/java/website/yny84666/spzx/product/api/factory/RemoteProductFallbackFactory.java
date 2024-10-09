package website.yny84666.spzx.product.api.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.product.api.RemoteProductService;
import website.yny84666.spzx.product.api.domain.vo.ProductSku;

import java.util.List;

@Component
public class RemoteProductFallbackFactory implements FallbackFactory<RemoteProductService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteProductFallbackFactory.class);

    @Override
    public RemoteProductService create(Throwable cause) {
        log.error("商品服务调用失败：{}",cause.getMessage());
        return new RemoteProductService() {
            @Override
            public R<List<ProductSku>> getTopSale(String source) {
                return null;
            }
        };
    }
}
