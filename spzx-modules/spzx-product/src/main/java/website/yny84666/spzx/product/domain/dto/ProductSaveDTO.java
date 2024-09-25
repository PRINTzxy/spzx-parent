package website.yny84666.spzx.product.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import website.yny84666.spzx.product.domain.Product;
import website.yny84666.spzx.product.domain.ProductSku;

import java.util.List;

@Data
@Schema(description = "新增商品数据模型")
public class ProductSaveDTO extends Product {
    @Schema(description = "详情图地址列表")
    private List<String> detailsImageUrlList;

    private List<ProductSku> productSkuList;
}
