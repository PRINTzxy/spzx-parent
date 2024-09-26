package website.yny84666.spzx.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

/**
 * 商品规格
 * @TableName product_spec
 */
@TableName(value ="product_spec")
@Data
public class ProductSpec extends BaseEntity implements Serializable {
    /**
     * 分类id
     */
    @Schema(description = "分类ID")
    @NotNull(message = "分类ID不能为空")
    private Long categoryId;

    /**
     * 规格名称
     */
    @Schema(description = "规格名称")
    @NotBlank(message = "规格名称不能为空")
    @Size(min = 0, max = 64, message = "规格名称长度不能超过64个字符")
    private String specName;

    /**
     * 规格值："[{"key":"颜色","valueList":["蓝","白","红"]]"
     */
    @Schema(description = "规格值")
    @NotBlank(message = "规格值不能为空")
    @Size(min = 0, max = 200, message = "规格值长度不能超过200个字符")
    private String specValue;

    @Schema(description = "分类名称")
    @TableField(exist = false)
    private String categoryName;

    @Schema(description = "分类id列表")
    @TableField(exist = false)
    private List<Long> categoryIdList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}