package cn.edu.guet.cantin.controller;

import cn.edu.guet.cantin.domain.OrderInfo;
import cn.edu.guet.cantin.domain.OrderDish;
import cn.edu.guet.cantin.http.HttpResult;
import cn.edu.guet.cantin.service.OrderInfoService;
import cn.edu.guet.cantin.service.OrderDishService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单管理控制器
 * 提供订单的增删改查等操作
 */
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class OrderController {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private OrderDishService orderDishService;

    /**
     * 获取所有订单列表
     * @return 订单列表
     */
    @GetMapping("/list")
    public HttpResult getAllOrders() {
        try {
            List<OrderInfo> orders = orderInfoService.list();
            return HttpResult.ok(orders);
        } catch (Exception e) {
            return HttpResult.error("获取订单列表失败: " + e.getMessage());
        }
    }

    /**
     * 分页获取订单列表
     * @param current 当前页
     * @param size 每页大小
     * @return 分页订单列表
     */
    @GetMapping("/page")
    public HttpResult getOrderPage(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size) {
        try {
            Page<OrderInfo> page = new Page<>(current, size);
            Page<OrderInfo> result = orderInfoService.page(page);
            return HttpResult.ok(result);
        } catch (Exception e) {
            return HttpResult.error("分页获取订单失败: " + e.getMessage());
        }
    }

    /**
     * 根据ID获取订单信息
     * @param orderId 订单ID
     * @return 订单信息
     */
    @GetMapping("/{orderId}")
    public HttpResult getOrderById(@PathVariable String orderId) {
        try {
            OrderInfo order = orderInfoService.getById(orderId);
            if (order != null) {
                // 获取订单菜品信息
                List<OrderDish> orderDishes = orderDishService.getByOrderId(orderId);
                Map<String, Object> result = Map.of(
                    "order", order,
                    "dishes", orderDishes
                );
                return HttpResult.ok(result);
            } else {
                return HttpResult.error("订单不存在");
            }
        } catch (Exception e) {
            return HttpResult.error("获取订单信息失败: " + e.getMessage());
        }
    }

    /**
     * 根据用户ID获取订单列表
     * @param userId 用户ID
     * @return 用户订单列表
     */
    @GetMapping("/user/{userId}")
    public HttpResult getOrdersByUserId(@PathVariable String userId) {
        try {
            List<OrderInfo> orders = orderInfoService.getByUserId(Integer.valueOf(userId));
            return HttpResult.ok(orders);
        } catch (Exception e) {
            return HttpResult.error("获取用户订单失败: " + e.getMessage());
        }
    }

    /**
     * 根据状态获取订单列表
     * @param status 订单状态
     * @return 订单列表
     */
    @GetMapping("/status/{status}")
    public HttpResult getOrdersByStatus(@PathVariable String status) {
        try {
            List<OrderInfo> orders = orderInfoService.getByStatus(status);
            return HttpResult.ok(orders);
        } catch (Exception e) {
            return HttpResult.error("获取订单状态失败: " + e.getMessage());
        }
    }

    /**
     * 创建新订单
     * @param orderData 订单数据
     * @return 创建结果
     */
    @PostMapping("/create")
    public HttpResult createOrder(@RequestBody Map<String, Object> orderData) {
        try {
            // 验证必填字段
            if (orderData.get("userId") == null) {
                return HttpResult.error("用户ID不能为空");
            }
            if (orderData.get("tableId") == null) {
                return HttpResult.error("桌位ID不能为空");
            }
            if (orderData.get("peopleCount") == null || (Integer) orderData.get("peopleCount") <= 0) {
                return HttpResult.error("就餐人数必须大于0");
            }

            // 创建订单
            OrderInfo order = new OrderInfo();
            order.setUserId((Integer) orderData.get("userId"));
            order.setTableId((Integer) orderData.get("tableId"));
            order.setPeopleCount((Integer) orderData.get("peopleCount"));
            order.setOrderTime(new java.util.Date());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); // 匹配前端传递的格式
            String startTimeStr = (String) orderData.get("reservationStartTime");
            String endTimeStr = (String) orderData.get("reservationEndTime");
            order.setReservationStartTime(sdf.parse(startTimeStr));
            order.setReservationEndTime(sdf.parse(endTimeStr));


            if (orderData.get("totalAmount") != null) {
                // 先转为字符串，再构造为 BigDecimal（兼容整数、小数、字符串多种类型）
                String amountStr = orderData.get("totalAmount").toString();
                order.setTotalAmount(new BigDecimal(amountStr));
            } else {
                // 若前端未传递，可设为默认值 0
                order.setTotalAmount(BigDecimal.ZERO);
            }

            order.setStatus("pending");
            order.setRemarks((String) orderData.get("remarks"));

            boolean success = orderInfoService.save(order);
            if (success) {
                return HttpResult.ok("订单创建成功", order);
            } else {
                return HttpResult.error("订单创建失败");
            }
        } catch (Exception e) {
            return HttpResult.error("创建订单失败: " + e.getMessage());
        }
    }

    /**
     * 更新订单信息
     * @param orderId 订单ID
     * @param orderInfo 更新的订单信息
     * @return 更新结果
     */
    @PutMapping("/{orderId}")
    public HttpResult updateOrder(@PathVariable String orderId, @RequestBody OrderInfo orderInfo) {
        try {
            // 检查订单是否存在
            OrderInfo existingOrder = orderInfoService.getById(orderId);
            if (existingOrder == null) {
                return HttpResult.error("订单不存在");
            }

            // 设置订单ID
            orderInfo.setOrderId(Integer.valueOf(orderId));

            // 验证必填字段
            if (orderInfo.getPeopleCount() != null && orderInfo.getPeopleCount() <= 0) {
                return HttpResult.error("就餐人数必须大于0");
            }

            boolean success = orderInfoService.updateById(orderInfo);
            if (success) {
                return HttpResult.ok("订单更新成功", orderInfo);
            } else {
                return HttpResult.error("订单更新失败");
            }
        } catch (Exception e) {
            return HttpResult.error("更新订单失败: " + e.getMessage());
        }
    }

    /**
     * 更新订单状态
     * @param orderId 订单ID
     * @param status 新状态
     * @return 更新结果
     */
    @PutMapping("/{orderId}/status")
    public HttpResult updateOrderStatus(@PathVariable String orderId, @RequestParam String status) {
        try {
            // 检查订单是否存在
            OrderInfo order = orderInfoService.getById(orderId);
            if (order == null) {
                return HttpResult.error("订单不存在");
            }

            // 验证状态值
            if (!isValidStatus(status)) {
                return HttpResult.error("无效的订单状态");
            }

            order.setStatus(status);
            boolean success = orderInfoService.updateById(order);
            if (success) {
                return HttpResult.ok("订单状态更新成功", order);
            } else {
                return HttpResult.error("订单状态更新失败");
            }
        } catch (Exception e) {
            return HttpResult.error("更新订单状态失败: " + e.getMessage());
        }
    }

    /**
     * 确认订单
     * @param orderId 订单ID
     * @return 确认结果
     */
    @PostMapping("/confirm/{orderId}")
    public HttpResult confirmOrder(@PathVariable String orderId) {
        try {
            OrderInfo order = orderInfoService.getById(orderId);
            if (order == null) {
                return HttpResult.error("订单不存在");
            }

            if (!"pending".equals(order.getStatus())) {
                return HttpResult.error("只有待确认状态的订单才能确认");
            }

            order.setStatus("confirmed");
            boolean success = orderInfoService.updateById(order);
            if (success) {
                return HttpResult.ok("订单确认成功", order);
            } else {
                return HttpResult.error("订单确认失败");
            }
        } catch (Exception e) {
            return HttpResult.error("确认订单失败: " + e.getMessage());
        }
    }

    /**
     * 完成订单
     * @param orderId 订单ID
     * @return 完成结果
     */
    @PutMapping("/{orderId}/complete")
    public HttpResult completeOrder(@PathVariable String orderId) {
        try {
            OrderInfo order = orderInfoService.getById(orderId);
            if (order == null) {
                return HttpResult.error("订单不存在");
            }

            if (!"confirmed".equals(order.getStatus())) {
                return HttpResult.error("只有已确认状态的订单才能完成");
            }

            order.setStatus("completed");
            boolean success = orderInfoService.updateById(order);
            if (success) {
                return HttpResult.ok("订单完成成功", order);
            } else {
                return HttpResult.error("订单完成失败");
            }
        } catch (Exception e) {
            return HttpResult.error("完成订单失败: " + e.getMessage());
        }
    }

    /**
     * 取消订单
     * @param orderId 订单ID
     * @return 取消结果
     */
    @PutMapping("/{orderId}/cancel")
    public HttpResult cancelOrder(@PathVariable String orderId) {
        try {
            OrderInfo order = orderInfoService.getById(orderId);
            if (order == null) {
                return HttpResult.error("订单不存在");
            }

            if ("completed".equals(order.getStatus()) || "cancelled".equals(order.getStatus())) {
                return HttpResult.error("该订单状态不允许取消");
            }

            order.setStatus("cancelled");
            boolean success = orderInfoService.updateById(order);
            if (success) {
                return HttpResult.ok("订单取消成功", order);
            } else {
                return HttpResult.error("订单取消失败");
            }
        } catch (Exception e) {
            return HttpResult.error("取消订单失败: " + e.getMessage());
        }
    }

    /**
     * 删除订单
     * @param orderId 订单ID
     * @return 删除结果
     */
    @DeleteMapping("/{orderId}")
    public HttpResult deleteOrder(@PathVariable String orderId) {
        try {
            // 检查订单是否存在
            OrderInfo order = orderInfoService.getById(orderId);
            if (order == null) {
                return HttpResult.error("订单不存在");
            }

            // 检查订单状态，只有已取消的订单才能删除
            if (!"cancelled".equals(order.getStatus())) {
                return HttpResult.error("只有已取消状态的订单才能删除");
            }

            // 先删除订单菜品关联
            orderDishService.removeByOrderId(orderId);

            // 再删除订单
            boolean success = orderInfoService.removeById(orderId);
            if (success) {
                return HttpResult.ok("订单删除成功");
            } else {
                return HttpResult.error("订单删除失败");
            }
        } catch (Exception e) {
            return HttpResult.error("删除订单失败: " + e.getMessage());
        }
    }

    /**
     * 获取今日订单
     * @return 今日订单列表
     */
    @GetMapping("/today")
    public HttpResult getTodayOrders() {
        try {
            List<OrderInfo> orders = orderInfoService.getTodayOrders();
            return HttpResult.ok(orders);
        } catch (Exception e) {
            return HttpResult.error("获取今日订单失败: " + e.getMessage());
        }
    }

    /**
     * 获取订单统计信息
     * @return 统计信息
     */
    @GetMapping("/statistics")
    public HttpResult getOrderStatistics() {
        try {
            Object statistics = orderInfoService.getOrderStatistics();
            return HttpResult.ok(statistics);
        } catch (Exception e) {
            return HttpResult.error("获取订单统计失败: " + e.getMessage());
        }
    }

    /**
     * 获取用户订单统计
     * @param userId 用户ID
     * @return 用户统计信息
     */
    @GetMapping("/statistics/user/{userId}")
    public HttpResult getUserOrderStatistics(@PathVariable Integer userId) {
        try {
            Object statistics = orderInfoService.getUserOrderStatistics(userId);
            return HttpResult.ok(statistics);
        } catch (Exception e) {
            return HttpResult.error("获取用户订单统计失败: " + e.getMessage());
        }
    }

    /**
     * 搜索订单
     * @param keyword 搜索关键词
     * @return 搜索结果
     */
    @GetMapping("/search")
    public HttpResult searchOrders(@RequestParam String keyword) {
        try {
            List<OrderInfo> orders = orderInfoService.searchOrders(keyword);
            return HttpResult.ok(orders);
        } catch (Exception e) {
            return HttpResult.error("搜索订单失败: " + e.getMessage());
        }
    }

    /**
     * 验证订单状态是否有效
     * @param status 状态值
     * @return 是否有效
     */
    private boolean isValidStatus(String status) {
        return "pending".equals(status) ||
               "confirmed".equals(status) ||
               "completed".equals(status) ||
               "cancelled".equals(status);
    }
}
