package website.yny84666.spzx.product.api.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

/**
 * 商品sku
 * @TableName product_sku
 */
@TableName(value ="product_sku")
@Data
public class ProductSku extends BaseEntity implements Serializable {
    /**
     * 商品编号
     */
    @Schema(description = "商品编号")
    private String skuCode;

    /**
     * 
     */
    @Schema(description = "sku名称")
    private String skuName;

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    private Long productId;

    /**
     * 缩略图路径
     */
    @Schema(description = "缩略图路径")
    private String thumbImg;

    /**
     * 售价
     */
    @Schema(description = "售价")
    private BigDecimal salePrice;

    /**
     * 市场价
     */
    @Schema(description = "市场价")
    private BigDecimal marketPrice;

    /**
     * 成本价
     */
    @Schema(description = "成本价")
    private BigDecimal costPrice;

    /**
     * sku规格信息json
     */
    @Schema(description = "sku规格信息json")
    private String skuSpec;

    /**
     * 重量
     */
    @Schema(description = "重量")
    private BigDecimal weight;

    /**
     * 体积
     */
    @Schema(description = "体积")
    private BigDecimal volume;

    /**
     * 线上状态：0-初始值，1-上架，-1-自主下架
     */
    @Schema(description = "线上状态：0-初始值，1-上架，-1-自主下架")
    private Integer status;

    @TableField(exist = false)
    @Schema(description = "扩展属性：sku的库存数量")
    private Integer stockNum;

    @TableField(exist = false)
    @Schema(description = "扩展属性：sku的销量")
    private Integer saleNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}