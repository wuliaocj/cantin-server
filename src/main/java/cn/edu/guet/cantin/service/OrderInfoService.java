package cn.edu.guet.cantin.service;

import cn.edu.guet.cantin.domain.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 订单信息服务接口
 * 提供订单相关的业务操作
 */
public interface OrderInfoService extends IService<OrderInfo> {

    /**
     * 根据用户ID获取订单列表
     * @param userId 用户ID
     * @return 订单列表
     */
    List<OrderInfo> getByUserId(Integer userId);

    /**
     * 根据订单状态获取订单列表
     * @param status 订单状态
     * @return 订单列表
     */
    List<OrderInfo> getByStatus(String status);

    /**
     * 获取今日订单
     * @return 今日订单列表
     */
    List<OrderInfo> getTodayOrders();

    /**
     * 获取订单统计信息
     * @return 统计信息
     */
    Object getOrderStatistics();

    /**
     * 获取用户订单统计
     * @param userId 用户ID
     * @return 用户统计信息
     */
    Object getUserOrderStatistics(Integer userId);
    /**
     * 搜索订单
     * @param keyword 搜索关键词
     * @return 搜索结果
     */
    List<OrderInfo> searchOrders(String keyword);
    /**
     * 创建订单
     * @param orderInfo 订单信息
     * @return 是否创建成功
     */
    boolean createOrder(OrderInfo orderInfo);
    /**
     * 更新订单状态
     * @param orderId 订单ID
     * @param status 新状态
     * @return 是否更新成功
     */
    boolean updateOrderStatus(String orderId, String status);
    /**
     * 取消订单
     * @param orderId 订单ID
     * @return 是否取消成功
     */
    boolean cancelOrder(String orderId);
    /**
     * 确认订单
     * @param orderId 订单ID
     * @return 是否确认成功
     */
    boolean confirmOrder(String orderId);
    /**
     * 完成订单
     * @param orderId 订单ID
     * @return 是否完成成功
     */
    boolean completeOrder(String orderId);
}
