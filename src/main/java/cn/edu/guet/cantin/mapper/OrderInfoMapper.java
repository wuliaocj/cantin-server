package cn.edu.guet.cantin.mapper;

import cn.edu.guet.cantin.domain.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 曜
* @description 针对表【order_info(订单信息表)】的数据库操作Mapper
* @createDate 2025-08-12 16:05:30
* @Entity generator.domain.OrderInfo
*/
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    /**
     * 根据用户ID获取订单列表
     * @param userId 用户ID
     * @return 订单列表
     */
    List<OrderInfo> selectByUserId(@Param("userId") Integer userId);

    /**
     * 根据订单状态获取订单列表
     * @param status 订单状态
     * @return 订单列表
     */
    List<OrderInfo> selectByStatus(@Param("status") String status);

    /**
     * 获取今日订单
     * @return 今日订单列表
     */
    List<OrderInfo> selectTodayOrders();

    /**
     * 获取订单统计信息
     * @return 统计信息
     */
    Object getOrderStatistics();

    /**
     * 获取用户订单统计
     * @param userId 用户ID
     * @return 统计信息
     */
    Object getUserOrderStatistics(@Param("userId") Integer userId);
}




