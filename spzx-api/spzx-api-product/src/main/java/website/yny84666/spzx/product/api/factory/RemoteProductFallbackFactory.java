package website.yny84666.spzx.product.api.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.product.api.RemoteProductService;
import website.yny84666.spzx.product.api.domain.vo.*;

import java.util.List;
import java.util.Map;

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

            @Override
            public R<TableDataInfo> skuList(Integer pageNum, Integer pageSize, SkuQuery skuQuery, String source) {
                return R.fail("获取商品列表失败:" + cause.getMessage());
            }

            ///////////////////////////////////////////////////////////////////////////////////

            @Override
            public R<ProductSku> getProductSku(Long skuId, String source) {
                return R.fail("获取商品sku失败:" + cause.getMessage());
            }

            @Override
            public R<Product> getProduct(Long id, String source) {
                return R.fail("获取商品信息失败:" + cause.getMessage());
            }

            @Override
            public R<SkuPrice> getSkuPrice(Long skuId, String source) {
                return R.fail("获取商品sku价格失败:" + cause.getMessage());
            }

            @Override
            public R<ProductDetails> getProductDetails(Long id, String source) {
                return R.fail("获取商品详情失败:" + cause.getMessage());
            }

            @Override
            public R<Map<String, Long>> getSkuSpecValue(Long id, String source) {
                return R.fail("获取商品sku规格失败:" + cause.getMessage());
            }

            @Override
            public R<SkuStockVo> getSkuStock(Long skuId, String source) {
                return R.fail("获取商品sku库存失败:" + cause.getMessage());
            }
        };
    }
}
