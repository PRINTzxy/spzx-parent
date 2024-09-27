package website.yny84666.spzx.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

/**
 * 分类品牌
 * @TableName brand
 */
@TableName(value ="brand")
@Data
public class Brand extends BaseEntity implements Serializable {
    /**
     * 品牌名称
     */
    @NotBlank(message = "品牌名称不能为空")
    @Size(min = 0, max = 64, message = "品牌名称长度不能超过64个字符")
    @Schema(description = "品牌名称")
    private String name;

    /**
     * 品牌图标
     */
    @NotBlank(message = "品牌图标不能为空")
    @Size(min = 0, max = 200, message = "品牌图标长度不能超过200个字符")
    @Schema(description = "品牌图标")
    private String logo;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}