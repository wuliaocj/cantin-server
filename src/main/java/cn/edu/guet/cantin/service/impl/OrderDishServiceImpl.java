package cn.edu.guet.cantin.service.impl;

import cn.edu.guet.cantin.domain.OrderDish;
import cn.edu.guet.cantin.mapper.OrderDishMapper;
import cn.edu.guet.cantin.service.OrderDishService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 订单菜品关联服务实现类
 * 实现订单菜品关联的业务逻辑
 */
@Service
public class OrderDishServiceImpl extends ServiceImpl<OrderDishMapper, OrderDish>
    implements OrderDishService {

    @Override
    public List<OrderDish> getByOrderId(String orderId) {
        QueryWrapper<OrderDish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        return this.list(queryWrapper);
    }

    @Override
    public List<OrderDish> getByDishId(Integer dishId) {
        QueryWrapper<OrderDish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dish_id", dishId);
        return this.list(queryWrapper);
    }

    @Override
    public boolean batchAddOrderDishes(List<OrderDish> orderDishes) {
        if (orderDishes == null || orderDishes.isEmpty()) {
            return false;
        }
        return this.saveBatch(orderDishes);
    }

    @Override
    public boolean removeByOrderId(String orderId) {
        QueryWrapper<OrderDish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        return this.remove(queryWrapper);
    }

    @Override
    public boolean removeByDishId(Integer dishId) {
        QueryWrapper<OrderDish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dish_id", dishId);
        return this.remove(queryWrapper);
    }

    @Override
    public boolean updateQuantity(String orderId, Integer dishId, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            return false;
        }
        
        QueryWrapper<OrderDish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId)
                   .eq("dish_id", dishId);
        
        OrderDish orderDish = this.getOne(queryWrapper);
        if (orderDish != null) {
            orderDish.setQuantity(quantity);
            return this.updateById(orderDish);
        }
        return false;
    }

    @Override
    public Object getOrderDishStatistics(String orderId) {
        QueryWrapper<OrderDish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        
        List<OrderDish> orderDishes = this.list(queryWrapper);
        
        // 计算统计信息
        int totalDishes = orderDishes.size();
        int totalQuantity = orderDishes.stream().mapToInt(OrderDish::getQuantity).sum();
        double totalAmount = orderDishes.stream()
                .mapToDouble(od -> od.getUnitPrice().doubleValue() * od.getQuantity())
                .sum();
        
        return Map.of(
            "totalDishes", totalDishes,
            "totalQuantity", totalQuantity,
            "totalAmount", totalAmount
        );
    }
}




