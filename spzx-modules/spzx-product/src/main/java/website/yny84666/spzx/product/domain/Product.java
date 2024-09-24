package website.yny84666.spzx.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
    private String name;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 一级分类id
     */
    private Long category1Id;

    /**
     * 二级分类id
     */
    private Long category2Id;

    /**
     * 三级分类id
     */
    private Long category3Id;

    /**
     * 计量单位
     */
    private String unitName;

    /**
     * 轮播图
     */
    private String sliderUrls;

    /**
     * 商品规格json
     */
    private String specValue;

    /**
     * 线上状态：0-初始值，1-上架，-1-自主下架
     */
    private Integer status;

    /**
     * 审核状态：0-初始值，1-通过，-1-未通过
     */
    private Integer auditStatus;

    /**
     * 审核信息
     */
    private String auditMessage;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}