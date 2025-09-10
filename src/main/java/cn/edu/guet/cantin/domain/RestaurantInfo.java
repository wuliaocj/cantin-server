package cn.edu.guet.cantin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 餐厅基本信息表
 * @TableName restaurant_info
 */
@TableName(value ="restaurant_info")
@Data
public class RestaurantInfo {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer restaurantId;

    /**
     * 餐厅名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 营业时间
     */
    private String businessHours;

    /**
     * 餐厅简介
     */
    private String description;

    /**
     * 餐厅图片URL
     */
    private String imageUrl;

    /**
     * 评分（0-5.0）
     */
    private BigDecimal rating;
}
