package website.yny84666.spzx.product.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "分类品牌列表数据模型")
public class CategoryBrandVO {
    @Schema(description = "品牌名称")
    private String brandName;
    @Schema(description = "品牌图标")
    private String logo;
    @Schema(description = "分类名称")
    private String categoryName;
    @Schema(description = "分类品牌id")
    private Long id;
    @Schema(description = "分类品牌创建时间")
    private Date createTime;

}
