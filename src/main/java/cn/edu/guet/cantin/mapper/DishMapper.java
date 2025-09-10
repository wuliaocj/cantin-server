package cn.edu.guet.cantin.mapper;

import cn.edu.guet.cantin.domain.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 曜
* @description 针对表【dish(菜品信息表)】的数据库操作Mapper
* @createDate 2025-08-12 16:05:35
* @Entity generator.domain.Dish
*/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

    /**
     * 根据分类获取菜品列表
     * @param category 菜品分类
     * @return 菜品列表
     */
    List<Dish> selectByCategory(@Param("category") String category);

    /**
     * 获取推荐菜品列表
     * @return 推荐菜品列表
     */
    List<Dish> selectRecommendDishes();

    /**
     * 搜索菜品
     * @param keyword 搜索关键词
     * @return 搜索结果
     */
    List<Dish> searchDishes(@Param("keyword") String keyword);

    /**
     * 获取所有有库存的菜品
     * @return 菜品列表
     */
    List<Dish> selectAllAvailableDishes();

    /**
     * 根据餐厅ID获取菜品
     * @param restaurantId 餐厅ID
     * @return 菜品列表
     */
    List<Dish> selectByRestaurantId(@Param("restaurantId") Integer restaurantId);

    /**
     * 获取菜品分类列表
     * @return 分类列表
     */
    List<String> selectCategories();

    /**
     * 获取菜品统计信息
     * @return 统计信息
     */
    Object getDishStatistics();
}




