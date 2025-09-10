package cn.edu.guet.cantin.controller;

import cn.edu.guet.cantin.domain.Dish;
import cn.edu.guet.cantin.http.HttpResult;
import cn.edu.guet.cantin.service.DishService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
@CrossOrigin(origins = "*")
public class DishController {

    @Resource
    private DishService dishService;

    /**
     * 获取所有菜品列表
     * @return 菜品列表
     */
    @GetMapping("/list")
    public HttpResult getAllDishes() {
        try {
            List<Dish> dishes = dishService.list();
            return HttpResult.ok(dishes);
        } catch (Exception e) {
            return HttpResult.error("获取菜品列表失败: " + e.getMessage());
        }
    }

    /**
     * 根据分类获取菜品
     * @param category 菜品分类
     * @return 分类菜品列表
     */
    @GetMapping("/category/{category}")
    public HttpResult getDishesByCategory(@PathVariable String category) {
        try {
            List<Dish> dishes = dishService.getDishesByCategory(category);
            return HttpResult.ok(dishes);
        } catch (Exception e) {
            return HttpResult.error("获取分类菜品失败: " + e.getMessage());
        }
    }

    /**
     * 获取推荐菜品
     * @return 推荐菜品列表
     */
    @GetMapping("/recommend")
    public HttpResult getRecommendDishes() {
        try {
            List<Dish> dishes = dishService.getRecommendDishes();
            return HttpResult.ok(dishes);
        } catch (Exception e) {
            return HttpResult.error("获取推荐菜品失败: " + e.getMessage());
        }
    }

    /**
     * 根据ID获取菜品详情
     * @param dishId 菜品ID
     * @return 菜品详情
     */
    @GetMapping("/{dishId}")
    public HttpResult getDishById(@PathVariable Integer dishId) {
        try {
            Dish dish = dishService.getById(dishId);
            if (dish != null) {
                return HttpResult.ok(dish);
            } else {
                return HttpResult.error("菜品不存在");
            }
        } catch (Exception e) {
            return HttpResult.error("获取菜品详情失败: " + e.getMessage());
        }
    }

    /**
     * 搜索菜品
     * @param keyword 搜索关键词
     * @return 搜索结果
     */
    @GetMapping("/search")
    public HttpResult searchDishes(@RequestParam String keyword) {
        try {
            List<Dish> dishes = dishService.searchDishes(keyword);
            return HttpResult.ok(dishes);
        } catch (Exception e) {
            return HttpResult.error("搜索菜品失败: " + e.getMessage());
        }
    }

    /**
     * 添加菜品（管理员功能）
     * @param dish 菜品信息
     * @return 添加结果
     */
    @PostMapping("/add")
    public HttpResult addDish(@RequestBody Dish dish) {
        try {
            boolean success = dishService.save(dish);
            if (success) {
                return HttpResult.ok("菜品添加成功");
            } else {
                return HttpResult.error("菜品添加失败");
            }
        } catch (Exception e) {
            return HttpResult.error("菜品添加失败: " + e.getMessage());
        }
    }

    /**
     * 更新菜品（管理员功能）
     * @param dish 菜品信息
     * @return 更新结果
     */
    @PutMapping("/update")
    public HttpResult updateDish(@RequestBody Dish dish) {
        try {
            boolean success = dishService.updateById(dish);
            if (success) {
                return HttpResult.ok("菜品更新成功");
            } else {
                return HttpResult.error("菜品更新失败");
            }
        } catch (Exception e) {
            return HttpResult.error("菜品更新失败: " + e.getMessage());
        }
    }

    /**
     * 删除菜品（管理员功能）
     * @param dishId 菜品ID
     * @return 删除结果
     */
    @DeleteMapping("/{dishId}")
    public HttpResult deleteDish(@PathVariable Integer dishId) {
        try {
            boolean success = dishService.removeById(dishId);
            if (success) {
                return HttpResult.ok("菜品删除成功");
            } else {
                return HttpResult.error("菜品删除失败");
            }
        } catch (Exception e) {
            return HttpResult.error("菜品删除失败: " + e.getMessage());
        }
    }
}

