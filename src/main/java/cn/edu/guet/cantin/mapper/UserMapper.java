package cn.edu.guet.cantin.mapper;

import cn.edu.guet.cantin.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 曜
* @description 针对表【user(系统用户表)】的数据库操作Mapper
* @createDate 2025-08-12 16:05:16
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 用户信息
     */
    User selectByEmail(@Param("email") String email);

    /**
     * 根据手机号查询用户
     * @param phone 手机号
     * @return 用户信息
     */
    User selectByPhone(@Param("phone") String phone);

    /**
     * 检查邮箱是否已存在
     * @param email 邮箱
     * @return 用户数量
     */
    int countByEmail(@Param("email") String email);

    /**
     * 检查手机号是否已存在
     * @param phone 手机号
     * @return 用户数量
     */
    int countByPhone(@Param("phone") String phone);

    /**
     * 获取用户统计信息
     * @return 统计信息
     */
    Object getUserStatistics();
}




