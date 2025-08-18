package cn.edu.guet.cantin.controller;

import cn.edu.guet.cantin.domain.User;
import cn.edu.guet.cantin.http.HttpResult;
import cn.edu.guet.cantin.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     * @param map 包含email和password的Map
     * @return 登录结果
     */
    @PostMapping("/login")
    public HttpResult login(@RequestBody Map<String, String> map) {
        try {
            // 查找数据库是否注册
            User user1 = userService.isRegister(map.get("email"));
            System.out.println(user1.toString());
            if (user1 != null) {
                // 判断账号密码
                if (user1.getPassword().equals(map.get("password"))) {
                    System.out.println("登录成功");
                    return HttpResult.ok(user1);
                } else {
                    return HttpResult.error("密码错误");
                }
            } else {
                return HttpResult.error("用户不存在");
            }
        } catch (Exception e) {
            return HttpResult.error("登录失败: " + e.getMessage());
        }
    }

    /**
     * 用户注册
     * @param user 用户信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public HttpResult register(@RequestBody User user) {
        try {
            User existingUser = userService.isRegister(user.getEmail());
            if (existingUser != null) {
                return HttpResult.error("邮箱已被注册");
            } else {
                User savedUser = userService.saveUser(user);
                if (savedUser != null) {
                    return HttpResult.ok("注册成功", savedUser);
                } else {
                    return HttpResult.error("注册失败");
                }
            }
        } catch (Exception e) {
            return HttpResult.error("注册失败: " + e.getMessage());
        }
    }

    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/{userId}")
    public HttpResult getUserInfo(@PathVariable Integer userId) {
        try {
            User user = userService.getById(userId);
            if (user != null) {
                return HttpResult.ok(user);
            } else {
                return HttpResult.error("用户不存在");
            }
        } catch (Exception e) {
            return HttpResult.error("获取用户信息失败: " + e.getMessage());
        }
    }

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 更新结果
     */
    @PutMapping("/update")
    public HttpResult updateUser(@RequestBody User user) {
        try {
            boolean success = userService.updateById(user);
            if (success) {
                return HttpResult.ok("用户信息更新成功");
            } else {
                return HttpResult.error("用户信息更新失败");
            }
        } catch (Exception e) {
            return HttpResult.error("用户信息更新失败: " + e.getMessage());
        }
    }

    /**
     * 分页查询用户列表
     */
    @GetMapping("/list")
    public HttpResult listUsers(@RequestParam(defaultValue = "1") Integer current,
                                @RequestParam(defaultValue = "10") Integer size,
                                @RequestParam(required = false) String keyword,
                                @RequestParam(required = false) Integer status) {
        try {
            Page<User> page = new Page<>(current, size);
            QueryWrapper<User> qw = new QueryWrapper<>();
            if (keyword != null && !keyword.isEmpty()) {
                qw.like("username", keyword).or().like("email", keyword).or().like("phone", keyword);
            }
            if (status != null) {
                qw.eq("status", status);
            }
            Page<User> result = userService.page(page, qw);
            return HttpResult.ok(result);
        } catch (Exception e) {
            return HttpResult.error("获取用户列表失败: " + e.getMessage());
        }
    }

    /**
     * 更新用户状态（启用/禁用）
     */
    @PutMapping("/{userId}/status")
    public HttpResult updateUserStatus(@PathVariable Integer userId, @RequestParam Integer status) {
        try {
            User user = userService.getById(userId);
            if (user == null) return HttpResult.error("用户不存在");
            user.setStatus(status);
            boolean success = userService.updateById(user);
            return success ? HttpResult.ok("状态更新成功") : HttpResult.error("状态更新失败");
        } catch (Exception e) {
            return HttpResult.error("状态更新失败: " + e.getMessage());
        }
    }
}
