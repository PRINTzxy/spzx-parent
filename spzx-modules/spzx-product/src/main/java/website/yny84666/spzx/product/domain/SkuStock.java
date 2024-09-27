package website.yny84666.spzx.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

/**
 * 商品sku库存表
 * @TableName sku_stock
 */
@TableName(value ="sku_stock")
@Data
public class SkuStock extends BaseEntity implements Serializable {
    /**
     * 商品ID
     */
    @Schema(description = "skuID")
    private Long skuId;

    /**
     * 总库存数
     */
    @Schema(description = "总库存数")
    private Integer totalNum;

    /**
     * 锁定库存
     */
    @Schema(description = "锁定库存数")
    private Integer lockNum;

    /**
     * 可用库存
     */
    @Schema(description = "可用库存数")
    private Integer availableNum;

    /**
     * 销量
     */
    @Schema(description = "销量")
    private Integer saleNum;

    /**
     * 线上状态：0-初始值，1-上架，-1-自主下架
     */
    @Schema(description = "线上状态：0-初始值，1-上架，-1-自主下架")
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}