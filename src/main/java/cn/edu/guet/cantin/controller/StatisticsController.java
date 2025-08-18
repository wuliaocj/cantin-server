package cn.edu.guet.cantin.controller;

import cn.edu.guet.cantin.http.HttpResult;
import cn.edu.guet.cantin.mapper.DishMapper;
import cn.edu.guet.cantin.mapper.OrderInfoMapper;
import cn.edu.guet.cantin.mapper.TableInfoMapper;
import cn.edu.guet.cantin.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@CrossOrigin(origins = "*")
public class StatisticsController {

    @Resource
    private DishMapper dishMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private TableInfoMapper tableInfoMapper;

    /**
     * 获取系统总体统计信息
     * @return 统计信息
     */
    @GetMapping("/overview")
    public HttpResult getOverviewStatistics() {
        try {
            Map<String, Object> statistics = new HashMap<>();
            
            // 获取菜品统计
            Object dishStats = dishMapper.getDishStatistics();
            statistics.put("dish", dishStats);
            
            // 获取用户统计
            Object userStats = userMapper.getUserStatistics();
            statistics.put("user", userStats);
            
            // 获取订单统计
            Object orderStats = orderInfoMapper.getOrderStatistics();
            statistics.put("order", orderStats);
            
            // 获取桌位统计
            Object tableStats = tableInfoMapper.getTableStatistics();
            statistics.put("table", tableStats);
            
            return HttpResult.ok(statistics);
        } catch (Exception e) {
            return HttpResult.error("获取统计信息失败: " + e.getMessage());
        }
    }

    /**
     * 获取菜品统计信息
     * @return 菜品统计
     */
    @GetMapping("/dish")
    public HttpResult getDishStatistics() {
        try {
            Object statistics = dishMapper.getDishStatistics();
            return HttpResult.ok(statistics);
        } catch (Exception e) {
            return HttpResult.error("获取菜品统计失败: " + e.getMessage());
        }
    }

    /**
     * 获取用户统计信息
     * @return 用户统计
     */
    @GetMapping("/user")
    public HttpResult getUserStatistics() {
        try {
            Object statistics = userMapper.getUserStatistics();
            return HttpResult.ok(statistics);
        } catch (Exception e) {
            return HttpResult.error("获取用户统计失败: " + e.getMessage());
        }
    }

    /**
     * 获取订单统计信息
     * @return 订单统计
     */
    @GetMapping("/order")
    public HttpResult getOrderStatistics() {
        try {
            Object statistics = orderInfoMapper.getOrderStatistics();
            return HttpResult.ok(statistics);
        } catch (Exception e) {
            return HttpResult.error("获取订单统计失败: " + e.getMessage());
        }
    }

    /**
     * 获取桌位统计信息
     * @return 桌位统计
     */
    @GetMapping("/table")
    public HttpResult getTableStatistics() {
        try {
            Object statistics = tableInfoMapper.getTableStatistics();
            return HttpResult.ok(statistics);
        } catch (Exception e) {
            return HttpResult.error("获取桌位统计失败: " + e.getMessage());
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
     * 获取菜品分类列表
     * @return 分类列表
     */
    @GetMapping("/categories")
    public HttpResult getCategories() {
        try {
            var categories = dishMapper.selectCategories();
            return HttpResult.ok(categories);
        } catch (Exception e) {
            return HttpResult.error("获取分类列表失败: " + e.getMessage());
        }
    }
}

