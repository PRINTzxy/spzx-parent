package website.yny84666.spzx.channel.service.impl;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import website.yny84666.spzx.channel.domain.ItemVo;
import website.yny84666.spzx.channel.service.ItemService;
import website.yny84666.spzx.common.core.constant.SecurityConstants;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.common.core.exception.ServiceException;
import website.yny84666.spzx.product.api.RemoteProductService;
import website.yny84666.spzx.product.api.domain.vo.*;

import java.util.Arrays;
import java.util.Map;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Resource
    private RemoteProductService remoteProductService;

    @Override
    public ItemVo item(Long skuId) {
        ItemVo itemVo = new ItemVo();

        //获取sku信息
        R<ProductSku> productSkuResult = remoteProductService.getProductSku(skuId, SecurityConstants.INNER);
        if (R.FAIL == productSkuResult.getCode()) {
            throw new ServiceException(productSkuResult.getMsg());
        }
        ProductSku productSku = productSkuResult.getData();
        itemVo.setProductSku(productSku);

        //获取商品信息
        R<Product> productResult = remoteProductService.getProduct(productSku.getProductId(), SecurityConstants.INNER);
        if (R.FAIL == productResult.getCode()) {
            throw new ServiceException(productResult.getMsg());
        }
        Product product = productResult.getData();
        itemVo.setProduct(product);
        itemVo.setSliderUrlList(Arrays.asList(product.getSliderUrls().split(",")));
        itemVo.setSpecValueList(JSON.parseArray(product.getSpecValue()));

        //获取商品最新价格
        R<SkuPrice> skuPriceResult = remoteProductService.getSkuPrice(skuId, SecurityConstants.INNER);
        if (R.FAIL == skuPriceResult.getCode()) {
            throw new ServiceException(skuPriceResult.getMsg());
        }
        SkuPrice skuPrice = skuPriceResult.getData();
        itemVo.setSkuPrice(skuPrice);

        //获取商品详情
        R<ProductDetails> productDetailsResult = remoteProductService.getProductDetails(productSku.getProductId(), SecurityConstants.INNER);
        if (R.FAIL == productDetailsResult.getCode()) {
            throw new ServiceException(productDetailsResult.getMsg());
        }
        ProductDetails productDetails = productDetailsResult.getData();
        itemVo.setDetailsImageUrlList(Arrays.asList(productDetails.getImageUrls().split(",")));

        //获取商品规格对应商品skuId信息
        R<Map<String, Long>> skuSpecValueResult = remoteProductService.getSkuSpecValue(productSku.getProductId(), SecurityConstants.INNER);
        if (R.FAIL == skuSpecValueResult.getCode()) {
            throw new ServiceException(skuSpecValueResult.getMsg());
        }
        Map<String, Long> skuSpecValueMap = skuSpecValueResult.getData();
        itemVo.setSkuSpecValueMap(skuSpecValueMap);

        //获取商品库存信息
        R<SkuStockVo> skuStockResult = remoteProductService.getSkuStock(skuId, SecurityConstants.INNER);
        if (R.FAIL == skuStockResult.getCode()) {
            throw new ServiceException(skuStockResult.getMsg());
        }
        SkuStockVo skuStockVo = skuStockResult.getData();
        itemVo.setSkuStockVo(skuStockVo);
        return itemVo;
    }
}
