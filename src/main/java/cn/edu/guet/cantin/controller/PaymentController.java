package cn.edu.guet.cantin.controller;

import cn.edu.guet.cantin.http.HttpResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    /**
     * 押金支付
     * 请求参数：{ orderId, userId, amount }
     */
    @PostMapping("/deposit")
    public HttpResult payDeposit(@RequestBody Map<String, Object> body) {
        try {
            Object orderId = body.get("orderId");
            Object userId = body.get("userId");
            Object amount = body.get("amount");
            if (orderId == null || userId == null || amount == null) {
                return HttpResult.error("缺少必要参数");
            }
            BigDecimal payAmount = new BigDecimal(amount.toString());
            if (payAmount.compareTo(BigDecimal.ZERO) <= 0) {
                return HttpResult.error("支付金额必须大于0");
            }
            // 此处可接入真实支付网关，当前直接返回成功
            return HttpResult.ok("押金支付成功");
        } catch (Exception e) {
            return HttpResult.error("押金支付失败: " + e.getMessage());
        }
    }

    /**
     * 订单消费支付
     * 请求参数：{ orderId, userId, amount }
     */
    @PostMapping("/order")
    public HttpResult payOrder(@RequestBody Map<String, Object> body) {
        try {
            Object orderId = body.get("orderId");
            Object userId = body.get("userId");
            Object amount = body.get("amount");
            if (orderId == null || userId == null || amount == null) {
                return HttpResult.error("缺少必要参数");
            }
            BigDecimal payAmount = new BigDecimal(amount.toString());
            if (payAmount.compareTo(BigDecimal.ZERO) <= 0) {
                return HttpResult.error("支付金额必须大于0");
            }
            // 此处可接入真实支付网关，当前直接返回成功
            return HttpResult.ok("订单支付成功");
        } catch (Exception e) {
            return HttpResult.error("订单支付失败: " + e.getMessage());
        }
    }
}




