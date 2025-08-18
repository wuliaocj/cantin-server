package cn.edu.guet.cantin.service.impl;

import cn.edu.guet.cantin.domain.User;
import cn.edu.guet.cantin.mapper.UserMapper;
import cn.edu.guet.cantin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 曜
* @description 针对表【user(系统用户表)】的数据库操作Service实现
* @createDate 2025-08-12 16:05:16
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User isRegister(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        userMapper.insert(user);
        return user;
    }
}




