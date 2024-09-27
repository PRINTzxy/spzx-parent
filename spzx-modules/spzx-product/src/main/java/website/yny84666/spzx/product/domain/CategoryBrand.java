package website.yny84666.spzx.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

/**
 * 分类品牌
 * @TableName category_brand
 */
@TableName(value ="category_brand")
@Data
public class CategoryBrand extends BaseEntity implements Serializable {
    /**
     * 品牌ID
     */
    @Schema(description = "品牌ID")
    @NotNull(message = "品牌ID不能为空")
    private Long brandId;

    /**
     * 分类ID
     */
    @Schema(description = "分类ID")
    @NotNull(message = "分类ID不能为空")
    private Long categoryId;

    @Schema(description = "分类名称")
    @TableField(exist = false)
    private String categoryName;

    @Schema(description = "品牌名称")
    @TableField(exist = false)
    private String brandName;

    @Schema(description = "品牌图标")
    @TableField(exist = false)
    private String logo;

    @Schema(description = "分类id列表")
    @TableField(exist = false)
    private List<Long> categoryIdList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}