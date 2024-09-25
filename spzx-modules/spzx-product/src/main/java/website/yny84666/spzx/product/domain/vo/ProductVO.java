package website.yny84666.spzx.product.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import website.yny84666.spzx.product.domain.Product;

@Data
@Schema(description = "商品列表数据模型")
public class ProductVO extends Product {
    private String brandName;
    private String category1Name;
    private String category2Name;
    private String category3Name;
}
