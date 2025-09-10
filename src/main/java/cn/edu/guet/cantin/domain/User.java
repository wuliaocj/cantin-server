package cn.edu.guet.cantin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 系统用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号（注册账号）
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 加密存储的密码
     */
    private String password;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 用户状态（0:禁用 1:正常）
     */
    private Integer status;
}
