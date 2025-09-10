package cn.edu.guet.cantin.service;

import cn.edu.guet.cantin.domain.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 曜
* @description 针对表【dish(菜品信息表)】的数据库操作Service
* @createDate 2025-08-12 16:05:35
*/
public interface DishService extends IService<Dish> {

    /**
     * 根据分类获取菜品列表
     * @param category 菜品分类
     * @return 菜品列表
     */
    List<Dish> getDishesByCategory(String category);

    /**
     * 获取推荐菜品列表
     * @return 推荐菜品列表
     */
    List<Dish> getRecommendDishes();

    /**
     * 搜索菜品
     * @param keyword 搜索关键词
     * @return 搜索结果
     */
    List<Dish> searchDishes(String keyword);
}
