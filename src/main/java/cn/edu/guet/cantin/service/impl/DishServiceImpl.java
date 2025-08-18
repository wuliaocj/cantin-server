package cn.edu.guet.cantin.service.impl;

import cn.edu.guet.cantin.domain.Dish;
import cn.edu.guet.cantin.mapper.DishMapper;
import cn.edu.guet.cantin.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 曜
* @description 针对表【dish(菜品信息表)】的数据库操作Service实现
* @createDate 2025-08-12 16:05:35
*/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService {

    @Override
    public List<Dish> getDishesByCategory(String category) {
        return baseMapper.selectByCategory(category);
    }

    @Override
    public List<Dish> getRecommendDishes() {
        return baseMapper.selectRecommendDishes();
    }

    @Override
    public List<Dish> searchDishes(String keyword) {
        return baseMapper.searchDishes(keyword);
    }
}




