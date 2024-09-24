package website.yny84666.spzx.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
    private Long categoryId;

    /**
     * 规格名称
     */
    private String specName;

    /**
     * 规格值："[{"key":"颜色","valueList":["蓝","白","红"]]"
     */
    private String specValue;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}