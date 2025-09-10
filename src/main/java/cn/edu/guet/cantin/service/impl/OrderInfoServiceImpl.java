package cn.edu.guet.cantin.service.impl;

import cn.edu.guet.cantin.domain.OrderInfo;
import cn.edu.guet.cantin.mapper.OrderInfoMapper;
import cn.edu.guet.cantin.service.OrderInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单信息服务实现类
 * 实现订单相关的业务逻辑
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>
    implements OrderInfoService {

    @Override
    public List<OrderInfo> getByUserId(Integer userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<OrderInfo> getByStatus(String status) {
        return baseMapper.selectByStatus(status);
    }

    @Override
    public List<OrderInfo> getTodayOrders() {
        return baseMapper.selectTodayOrders();
    }

    @Override
    public Object getOrderStatistics() {
        return baseMapper.getOrderStatistics();
    }

    @Override
    public Object getUserOrderStatistics(Integer userId) {
        return baseMapper.getUserOrderStatistics(userId);
    }

    @Override
    public List<OrderInfo> searchOrders(String keyword) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("order_id", keyword)
                   .or()
                   .like("remark", keyword);
        return this.list(queryWrapper);
    }

    @Override
    public boolean createOrder(OrderInfo orderInfo) {
        // 设置订单时间
        if (orderInfo.getOrderTime() == null) {
            orderInfo.setOrderTime(new java.util.Date());
        }

        // 设置默认状态
        if (orderInfo.getStatus() == null) {
            orderInfo.setStatus("pending");
        }

        // 设置默认支付状态
//        if (orderInfo.getPaymentStatus() == null) {
//            orderInfo.setPaymentStatus(0);
//        }

        return this.save(orderInfo);
    }

    @Override
    public boolean updateOrderStatus(String orderId, String status) {
        OrderInfo order = this.getById(orderId);
        if (order != null) {
            order.setStatus(status);
            return this.updateById(order);
        }
        return false;
    }

    @Override
    public boolean cancelOrder(String orderId) {
        OrderInfo order = this.getById(orderId);
        if (order != null && !"completed".equals(order.getStatus()) && !"cancelled".equals(order.getStatus())) {
            order.setStatus("cancelled");
            return this.updateById(order);
        }
        return false;
    }

    @Override
    public boolean confirmOrder(String orderId) {
        OrderInfo order = this.getById(orderId);
        if (order != null && "pending".equals(order.getStatus())) {
            order.setStatus("confirmed");
            return this.updateById(order);
        }
        return false;
    }

    @Override
    public boolean completeOrder(String orderId) {
        OrderInfo order = this.getById(orderId);
        if (order != null && "confirmed".equals(order.getStatus())) {
            order.setStatus("completed");
            return this.updateById(order);
        }
        return false;
    }
}




