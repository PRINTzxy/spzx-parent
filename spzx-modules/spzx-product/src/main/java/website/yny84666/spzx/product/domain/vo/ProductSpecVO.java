package website.yny84666.spzx.product.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import website.yny84666.spzx.product.domain.ProductSpec;

@Data
@Schema(description = "商品规格属性值列表数据模型")
public class ProductSpecVO extends ProductSpec {
    private String categoryName;
}
