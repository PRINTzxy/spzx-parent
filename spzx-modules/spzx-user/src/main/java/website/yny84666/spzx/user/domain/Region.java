package website.yny84666.spzx.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

/**
 * 地区信息表
 * @TableName region
 */
@TableName(value ="region")
@Data
public class Region extends BaseEntity implements Serializable {
    /**
     * 地区编码
     */
    private String code;

    /**
     * 上级地区code
     */
    private String parentCode;

    /**
     * 地区名称
     */
    private String name;

    /**
     * 地区级别：1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县
     */
    private Integer level;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}