package website.yny84666.spzx.product.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import website.yny84666.spzx.product.domain.Product;
import website.yny84666.spzx.product.domain.ProductSku;

import java.util.List;

@Data
@Schema(description = "商品详情数据模型")
public class ProductDetailVO extends Product {
//    private String brandName;
//    private String category1Name;
//    private String category2Name;
//    private String category3Name;
//
//    @Schema(description = "详情图地址列表")
//    private List<String> detailsImageUrlList;
//
//    private List<ProductSku> productSkuList;
}
