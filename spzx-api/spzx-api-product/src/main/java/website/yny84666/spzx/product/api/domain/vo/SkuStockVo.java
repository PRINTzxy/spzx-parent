package website.yny84666.spzx.product.api.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SkuStockVo {
    /** 商品ID */
    @Schema(name = "商品ID")
    private Long skuId;

    /** 可用库存数 */
    @Schema(name = "可用库存数")
    private Integer availableNum;

    /** 销量 */
    @Schema(name = "销量")
    private Integer saleNum;
}
