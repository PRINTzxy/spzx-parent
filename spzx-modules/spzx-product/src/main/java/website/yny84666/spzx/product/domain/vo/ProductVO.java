package website.yny84666.spzx.product.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import website.yny84666.spzx.product.domain.Product;

@Data
@Schema(description = "商品列表数据模型")
public class ProductVO extends Product {
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
}
