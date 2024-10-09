package website.yny84666.spzx.product.api.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVo {
    /** 分类id */
    @Schema(description = "分类id")
    private Long id;

    /** 分类名称 */
    @Schema(description = "分类名称")
    private String name;

    /** 图标地址 */
    @Schema(description = "图标地址")
    private String imageUrl;

    /** 上级分类id */
    @Schema(description = "上级分类id")
    private Long parentId;

    /** 子列表 */
    private List<CategoryVo> children;
}
