package website.yny84666.spzx.product.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "分类品牌详情数据模型")
public class CategoryBrandDetailVO {
    private Long id;
    private Long brandId;//品牌id
    private Long categoryId;//三级分类id
    @Schema(description = "回显一二三级分类使用的id集合")
    private List<Long> categoryIdList;
}
