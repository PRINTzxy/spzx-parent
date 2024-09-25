package website.yny84666.spzx.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

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
    @NotNull
    private Long brandId;

    /**
     * 分类ID
     */
    @NotNull
    private Long categoryId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}