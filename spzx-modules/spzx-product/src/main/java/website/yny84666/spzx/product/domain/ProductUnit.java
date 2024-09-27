package website.yny84666.spzx.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

/**
 * 商品单位
 * @TableName product_unit
 */
@TableName(value ="product_unit")
@Data
@Schema(description = "商品单位")
public class ProductUnit extends BaseEntity implements Serializable {
    /**
     * 名称
     * 非空+ 字符串集合数组长度不为0校验
     *     @NotNull 非空校验
     */
    @Schema(description = "商品单位名称")
    @NotBlank(message = "商品单位名称不能为空")
    @Size(min = 0, max = 10, message = "商品单位名称长度不能超过10个字符")
    private String name;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}