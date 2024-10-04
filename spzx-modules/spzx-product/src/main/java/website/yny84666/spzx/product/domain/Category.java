package website.yny84666.spzx.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import website.yny84666.spzx.common.core.annotation.Excel;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

/**
 * 商品分类
 * @TableName category
 */
@Schema(description = "商品分类")
@TableName(value ="category")
@Data
public class Category extends BaseEntity implements Serializable {
    /**
     * 分类名称
     */
    @Schema(description = "分类名称")
    private String name;

    /**
     * 
     */
    @Schema(description = "图标地址")
    private String imageUrl;

    /**
     * 父分类id
     */
    @Schema(description = "上级分类id")
    private Long parentId;

    /**
     * 是否显示[0-不显示，1显示]
     */
    @Excel(name = "是否显示", readConverterExp = "0=不显示,1=显示")
    @Schema(description = "是否显示[0-不显示，1显示]")
    private Integer status;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer orderNum;

    /** 是否有子节点 */
    @TableField(exist = false)
    private Boolean hasChildren;

    /** 子节点列表 */
    @TableField(exist = false)
    private List<Category> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}