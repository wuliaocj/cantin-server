package cn.edu.guet.cantin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthController {

    /**
     * 健康检查接口
     * @return 健康状态
     */
    @GetMapping
    public Map<String, Object> health() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "UP");
        result.put("message", "餐厅管理系统运行正常");
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }

    /**
     * 系统信息接口
     * @return 系统信息
     */
    @GetMapping("/info")
    public Map<String, Object> info() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", "王中王餐厅管理系统");
        result.put("version", "1.0.0");
        result.put("description", "基于Spring Boot + Vue 3的现代化餐厅管理系统");
        result.put("technology", "Spring Boot 3.4.9, MyBatis Plus, MySQL, Vue 3");
        return result;
    }
}

