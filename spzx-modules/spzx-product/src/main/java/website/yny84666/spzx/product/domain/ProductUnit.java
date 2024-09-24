package website.yny84666.spzx.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

/**
 * 商品单位
 * @TableName product_unit
 */
@TableName(value ="product_unit")
@Data
public class ProductUnit extends BaseEntity implements Serializable {
    /**
     * 名称
     * 非空+ 字符串集合数组长度不为0校验
     *     @NotNull 非空校验
     */
    @NotEmpty(message = "商品单位不能为空")
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}