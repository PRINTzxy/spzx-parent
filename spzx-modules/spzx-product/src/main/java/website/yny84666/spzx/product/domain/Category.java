package website.yny84666.spzx.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

/**
 * 商品分类
 * @TableName category
 */
@TableName(value ="category")
@Data
public class Category extends BaseEntity implements Serializable {
    /**
     * 分类名称
     */
    private String name;

    /**
     * 
     */
    private String imageUrl;

    /**
     * 父分类id
     */
    private Long parentId;

    /**
     * 是否显示[0-不显示，1显示]
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer orderNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}