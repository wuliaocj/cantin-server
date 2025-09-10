package cn.edu.guet.cantin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 桌位信息表
 * @TableName table_info
 */
@TableName(value ="table_info")
@Data
public class TableInfo {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer tableId;

    /**
     * 桌位编号（如A01、B05）
     */
    private String tableNumber;

    /**
     * 类型（window/standard/private等）
     */
    @TableField("table_type")
    private String tableType;

    /**
     * 容纳人数
     */
    private Integer capacity;

    /**
     * 位置描述（可选，数据库中没有此字段）
     */
    @TableField(exist = false)
    private String location;

    /**
     * 状态（available/reserved/occupied）
     */
    private String status;

    /**
     * 所属餐厅ID
     */
    private Integer restaurantId;
}
