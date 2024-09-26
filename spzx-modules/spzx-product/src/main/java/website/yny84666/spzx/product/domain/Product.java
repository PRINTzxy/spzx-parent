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
 * 商品
 * @TableName product
 */
@TableName(value ="product")
@Data
public class Product extends BaseEntity implements Serializable {
    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String name;

    /**
     * 品牌ID
     */
    @Schema(description = "品牌ID")
    private Long brandId;

    /**
     * 一级分类id
     */
    @Schema(description = "一级分类id")
    private Long category1Id;

    /**
     * 二级分类id
     */
    @Schema(description = "二级分类id")
    private Long category2Id;

    /**
     * 三级分类id
     */
    @Schema(description = "三级分类id")
    private Long category3Id;

    /**
     * 计量单位
     */
    @Schema(description = "计量单位")
    private String unitName;

    /**
     * 轮播图
     */
    @Schema(description = "轮播图")
    private String sliderUrls;

    /**
     * 商品规格json
     */
    @Schema(description = "商品规格json")
    private String specValue;

    /**
     * 线上状态：0-初始值，1-上架，-1-自主下架
     */
    @Schema(description = "线上状态：0-初始值，1-上架，-1-自主下架")
    private Integer status;

    /**
     * 审核状态：0-初始值，1-通过，-1-未通过
     */
    @Schema(description = "审核状态：0-初始值，1-通过，-1-未通过")
    private Integer auditStatus;

    /**
     * 审核信息
     */
    @Schema(description = "审核信息")
    private String auditMessage;

    /**
     * 品牌
     */
    @Schema(description = "品牌")
    @TableField(exist = false)
    private String brandName;
    /**
     * 一级分类
     */
    @Schema(description = "一级分类")
    @TableField(exist = false)
    private String category1Name;
    /**
     * 二级分类
     */
    @Schema(description = "二级分类")
    @TableField(exist = false)
    private String category2Name;
    /**
     * 三级分类
     */
    @Schema(description = "三级分类")
    @TableField(exist = false)
    private String category3Name;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}