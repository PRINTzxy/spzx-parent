package website.yny84666.spzx.product.api.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

/**
 * 商品sku属性表
 * @TableName product_details
 */
@Schema(description = "商品详情")
@TableName(value ="product_details")
@Data
public class ProductDetails extends BaseEntity implements Serializable {
    /**
     * 商品id
     */
    @Schema(description = "商品id")
    private Long productId;

    /**
     * 详情图片地址
     */
    @Schema(description = "详情图片地址")
    private String imageUrls;

//    private String delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}