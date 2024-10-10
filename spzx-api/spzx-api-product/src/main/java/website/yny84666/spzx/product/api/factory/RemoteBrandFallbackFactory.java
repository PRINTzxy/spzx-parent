package website.yny84666.spzx.product.api.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.product.api.RemoteBrandService;
import website.yny84666.spzx.product.api.domain.vo.Brand;

import java.util.List;

@Component
public class RemoteBrandFallbackFactory implements FallbackFactory<RemoteBrandService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteBrandFallbackFactory.class);

    @Override
    public RemoteBrandService create(Throwable cause) {
        log.error("商品服务调用失败:{}", cause.getMessage());
        return new RemoteBrandService() {
            @Override
            public R<List<Brand>> getBrandAllList(String source) {
                return R.fail("获取全部品牌失败：" + cause.getMessage());
            }
        };
    }
}
