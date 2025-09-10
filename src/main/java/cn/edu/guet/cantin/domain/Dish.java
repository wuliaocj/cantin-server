package cn.edu.guet.cantin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 菜品信息表
 * @TableName dish
 */
@TableName(value ="dish")
@Data
public class Dish {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer dishId;

    /**
     * 菜品名称
     */
    private String name;

    /**
     * 分类（主食/荤菜/素菜等）
     */
    private String category;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 菜品描述
     */
    private String description;

    /**
     * 菜品图片URL
     */
    private String imageUrl;

    /**
     * 是否推荐（0:否 1:是）
     */
    private Integer recommend;

    /**
     * 库存状态（0:缺货 1:正常）
     */
    private Integer stockStatus;

    /**
     * 所属餐厅ID
     */
    private Integer restaurantId;
}
