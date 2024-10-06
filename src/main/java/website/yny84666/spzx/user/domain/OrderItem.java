package website.yny84666.spzx.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

/**
 * 订单项信息
 * @TableName order_item
 */
@TableName(value ="order_item")
@Data
public class OrderItem extends BaseEntity implements Serializable {
    /**
     * order_id
     */
    private Long orderId;

    /**
     * 商品sku编号
     */
    private Long skuId;

    /**
     * 商品sku名字
     */
    private String skuName;

    /**
     * 商品sku图片
     */
    private String thumbImg;

    /**
     * 商品sku价格
     */
    private BigDecimal skuPrice;

    /**
     * 商品购买的数量
     */
    private Integer skuNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}