package cn.edu.guet.cantin.service;

import cn.edu.guet.cantin.domain.OrderDish;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 订单菜品关联服务接口
 * 提供订单菜品关联的业务操作
 */
public interface OrderDishService extends IService<OrderDish> {

    /**
     * 根据订单ID获取菜品列表
     * @param orderId 订单ID
     * @return 菜品列表
     */
    List<OrderDish> getByOrderId(String orderId);

    /**
     * 根据菜品ID获取订单列表
     * @param dishId 菜品ID
     * @return 订单列表
     */
    List<OrderDish> getByDishId(Integer dishId);

    /**
     * 批量添加订单菜品
     * @param orderDishes 订单菜品列表
     * @return 是否添加成功
     */
    boolean batchAddOrderDishes(List<OrderDish> orderDishes);

    /**
     * 根据订单ID删除菜品关联
     * @param orderId 订单ID
     * @return 是否删除成功
     */
    boolean removeByOrderId(String orderId);

    /**
     * 根据菜品ID删除订单关联
     * @param dishId 菜品ID
     * @return 是否删除成功
     */
    boolean removeByDishId(Integer dishId);

    /**
     * 更新订单菜品数量
     * @param orderId 订单ID
     * @param dishId 菜品ID
     * @param quantity 新数量
     * @return 是否更新成功
     */
    boolean updateQuantity(String orderId, Integer dishId, Integer quantity);

    /**
     * 获取订单菜品统计
     * @param orderId 订单ID
     * @return 统计信息
     */
    Object getOrderDishStatistics(String orderId);
}
