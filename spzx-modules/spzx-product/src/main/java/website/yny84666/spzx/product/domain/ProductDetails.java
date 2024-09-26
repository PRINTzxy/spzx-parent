package website.yny84666.spzx.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

/**
 * 商品sku属性表
 * @TableName product_details
 */
@TableName(value ="product_details")
@Data
public class ProductDetails extends BaseEntity implements Serializable {
    /**
     * 商品id
     */
    private Long productId;

    /**
     * 详情图片地址
     */
    private String imageUrls;

//    private String delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}