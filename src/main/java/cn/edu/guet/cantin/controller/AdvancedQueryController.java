package cn.edu.guet.cantin.controller;

import cn.edu.guet.cantin.http.HttpResult;
import cn.edu.guet.cantin.mapper.OrderDishMapper;
import cn.edu.guet.cantin.mapper.OrderInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/advanced-query")
@CrossOrigin(origins = "*")
public class AdvancedQueryController {

    @Resource
    private OrderDishMapper orderDishMapper;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    /**
     * 获取订单详情（包含菜品信息）
     * @param orderId 订单ID
     * @return 订单详情
     */
    @GetMapping("/order-details/{orderId}")
    public HttpResult getOrderDetails(@PathVariable Integer orderId) {
        try {
            List<Map<String, Object>> orderDetails = orderDishMapper.selectOrderDetails(orderId);
            Map<String, Object> statistics = orderDishMapper.selectOrderDishStatistics(orderId);
            
            Map<String, Object> result = Map.of(
                "orderDetails", orderDetails,
                "statistics", statistics
            );
            
            return HttpResult.ok(result);
        } catch (Exception e) {
            return HttpResult.error("获取订单详情失败: " + e.getMessage());
        }
    }

    /**
     * 获取热门菜品排行
     * @param limit 限制数量，默认10
     * @return 热门菜品列表
     */
    @GetMapping("/popular-dishes")
    public HttpResult getPopularDishes(@RequestParam(defaultValue = "10") Integer limit) {
        try {
            List<Map<String, Object>> popularDishes = orderDishMapper.selectPopularDishes(limit);
            return HttpResult.ok(popularDishes);
        } catch (Exception e) {
            return HttpResult.error("获取热门菜品失败: " + e.getMessage());
        }
    }

    /**
     * 获取菜品销售统计
     * @param startDate 开始日期 (YYYY-MM-DD)
     * @param endDate 结束日期 (YYYY-MM-DD)
     * @return 销售统计
     */
    @GetMapping("/dish-sales")
    public HttpResult getDishSalesStatistics(@RequestParam String startDate, 
                                            @RequestParam String endDate) {
        try {
            List<Map<String, Object>> salesStatistics = orderDishMapper.selectDishSalesStatistics(startDate, endDate);
            return HttpResult.ok(salesStatistics);
        } catch (Exception e) {
            return HttpResult.error("获取销售统计失败: " + e.getMessage());
        }
    }

    /**
     * 获取用户点餐偏好
     * @param userId 用户ID
     * @return 用户偏好菜品
     */
    @GetMapping("/user-preferences/{userId}")
    public HttpResult getUserPreferences(@PathVariable Integer userId) {
        try {
            List<Map<String, Object>> preferences = orderDishMapper.selectUserPreferences(userId);
            return HttpResult.ok(preferences);
        } catch (Exception e) {
            return HttpResult.error("获取用户偏好失败: " + e.getMessage());
        }
    }

    /**
     * 获取用户订单统计
     * @param userId 用户ID
     * @return 用户订单统计
     */
    @GetMapping("/user-order-stats/{userId}")
    public HttpResult getUserOrderStatistics(@PathVariable Integer userId) {
        try {
            Object statistics = orderInfoMapper.getUserOrderStatistics(userId);
            return HttpResult.ok(statistics);
        } catch (Exception e) {
            return HttpResult.error("获取用户订单统计失败: " + e.getMessage());
        }
    }

    /**
     * 获取今日订单
     * @return 今日订单列表
     */
    @GetMapping("/today-orders")
    public HttpResult getTodayOrders() {
        try {
            var orders = orderInfoMapper.selectTodayOrders();
            return HttpResult.ok(orders);
        } catch (Exception e) {
            return HttpResult.error("获取今日订单失败: " + e.getMessage());
        }
    }

    /**
     * 根据订单状态获取订单
     * @param status 订单状态
     * @return 订单列表
     */
    @GetMapping("/orders-by-status")
    public HttpResult getOrdersByStatus(@RequestParam String status) {
        try {
            var orders = orderInfoMapper.selectByStatus(status);
            return HttpResult.ok(orders);
        } catch (Exception e) {
            return HttpResult.error("获取订单失败: " + e.getMessage());
        }
    }
}

