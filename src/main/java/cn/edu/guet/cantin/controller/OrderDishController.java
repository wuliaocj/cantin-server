package cn.edu.guet.cantin.controller;

import cn.edu.guet.cantin.domain.OrderDish;
import cn.edu.guet.cantin.http.HttpResult;
import cn.edu.guet.cantin.service.OrderDishService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单菜品控制器：提供批量添加等接口
 */
@RestController
@RequestMapping({"/orderDish", "/order-dish"})
@CrossOrigin(origins = "*")
public class OrderDishController {

    @Resource
    private OrderDishService orderDishService;

    /**
     * 批量添加订单菜品明细
     * 前端 payload 示例：[{ orderId, dishId, quantity, unitPrice, subtotal }]
     */
    @PostMapping("/batchAdd")
    public HttpResult batchAdd(@RequestBody List<OrderDish> orderDishes) {
        try {
            if (orderDishes == null || orderDishes.isEmpty()) {
                return HttpResult.error("参数不能为空");
            }
            // 兜底计算小计与单价，避免统计为 0
            for (OrderDish od : orderDishes) {
                if (od.getQuantity() == null) {
                    od.setQuantity(1);
                }
                if (od.getUnitPrice() == null && od.getSubtotal() != null && od.getQuantity() != null && od.getQuantity() > 0) {
                    od.setUnitPrice(od.getSubtotal().divide(new java.math.BigDecimal(od.getQuantity())));
                }
                if (od.getSubtotal() == null && od.getUnitPrice() != null) {
                    od.setSubtotal(od.getUnitPrice().multiply(new java.math.BigDecimal(od.getQuantity())));
                }
            }
            boolean ok = orderDishService.batchAddOrderDishes(orderDishes);
            if (ok) {
                return HttpResult.ok("添加成功", orderDishes);
            }
            return HttpResult.error("添加失败");
        } catch (Exception e) {
            return HttpResult.error("服务异常: " + e.getMessage());
        }
    }
}


