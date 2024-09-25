package website.yny84666.spzx.product.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import website.yny84666.spzx.product.domain.ProductSpec;

import java.util.List;

@Data
@Schema(description = "商品规格参数详情数据模型")
public class ProductSpecDetailVO extends ProductSpec {
    @Schema(description = "回显一二三级分类使用的id集合")
    private List<Long> categoryIdList;
}
