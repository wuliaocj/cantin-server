package cn.edu.guet.cantin.service;

import cn.edu.guet.cantin.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 曜
* @description 针对表【user(系统用户表)】的数据库操作Service
* @createDate 2025-08-12 16:05:16
*/
public interface UserService extends IService<User> {
    User isRegister(String username);

    User saveUser(User user);

    User findByUsernameOrEmail(String usernameOrEmail);
}
