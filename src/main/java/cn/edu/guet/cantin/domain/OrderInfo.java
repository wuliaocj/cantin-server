package cn.edu.guet.cantin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 订单信息表
 * @TableName order_info
 */
@TableName(value ="order_info")
@Data
public class OrderInfo {
    /**
     * 订单编号（唯一标识）
     */
    @TableId(type = IdType.AUTO)
    private Integer orderId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 预订桌位ID
     */
    private Integer tableId;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 预订到店时间
     */
    @TableField("reservation_time")
    private Date reservationTime;

    /**
     * 就餐人数
     */
    @TableField("people_count")
    private Integer peopleCount;

    /**
     * 订单总额
     */
    @TableField("total_amount")
    private BigDecimal totalAmount;

    /**
     * 订单状态（pending/confirmed/completed/cancelled）
     */
    private String status;

    /**
     * 特殊备注
     */
    @TableField("remarks")
    private String remarks;
}
