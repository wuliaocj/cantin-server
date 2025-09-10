package cn.edu.guet.cantin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 订单菜品关联表
 * @TableName order_dish
 */
@TableName(value ="order_dish")
@Data
public class OrderDish {
    /**
     * 订单菜品ID
     */
    @TableId(type = IdType.AUTO)
    private Integer orderDishId;

    /**
     * 订单编号
     */
    @TableField("order_id")
    private Integer orderId;

    /**
     * 菜品ID
     */
    @TableField("dish_id")
    private Integer dishId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 单价
     */
    @TableField("unit_price")
    private BigDecimal unitPrice;

    /**
     * 小计
     */
    private BigDecimal subtotal;
}
