package cn.edu.guet.cantin.mapper;

import cn.edu.guet.cantin.domain.OrderDish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author 曜
* @description 针对表【order_dish(订单菜品关联表)】的数据库操作Mapper
* @createDate 2025-08-12 16:05:30
* @Entity generator.domain.OrderDish
*/
@Mapper
public interface OrderDishMapper extends BaseMapper<OrderDish> {

    /**
     * 获取订单详情（包含菜品信息）
     * @param orderId 订单ID
     * @return 订单详情列表
     */
    List<Map<String, Object>> selectOrderDetails(@Param("orderId") Integer orderId);

    /**
     * 获取热门菜品排行
     * @param limit 限制数量
     * @return 热门菜品列表
     */
    List<Map<String, Object>> selectPopularDishes(@Param("limit") Integer limit);

    /**
     * 获取菜品销售统计
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 销售统计
     */
    List<Map<String, Object>> selectDishSalesStatistics(@Param("startDate") String startDate, 
                                                        @Param("endDate") String endDate);

    /**
     * 获取用户点餐偏好
     * @param userId 用户ID
     * @return 用户偏好菜品
     */
    List<Map<String, Object>> selectUserPreferences(@Param("userId") Integer userId);

    /**
     * 获取订单菜品统计
     * @param orderId 订单ID
     * @return 统计信息
     */
    Map<String, Object> selectOrderDishStatistics(@Param("orderId") Integer orderId);
}




