package cn.edu.guet.cantin.config;

import cn.edu.guet.cantin.domain.Dish;
import cn.edu.guet.cantin.service.DishService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 数据初始化器
 * 在应用启动时初始化示例数据
 */
@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    @Resource
    private DishService dishService;

    @Override
    public void run(String... args) throws Exception {
        log.info("开始初始化菜品数据...");
        initDishData();
        log.info("菜品数据初始化完成！");
    }

    /**
     * 初始化菜品数据
     */
    private void initDishData() {
        // 检查是否已有数据
        long count = dishService.count();
        if (count > 0) {
            log.info("菜品数据已存在，跳过初始化");
            return;
        }

        List<Dish> dishes = Arrays.asList(
            // 主食类
            createDish("宫保鸡丁", "主食", new BigDecimal("28.00"),
                "经典川菜，鸡肉鲜嫩，花生香脆，口感丰富",
                "https://picsum.photos/id/292/300/200", 1, 1, 1),

            createDish("麻婆豆腐", "主食", new BigDecimal("18.00"),
                "四川传统名菜，豆腐嫩滑，麻辣鲜香",
                "https://picsum.photos/id/293/300/200", 1, 1, 1),

            createDish("糖醋里脊", "主食", new BigDecimal("32.00"),
                "外酥内嫩，酸甜可口，开胃下饭",
                "https://picsum.photos/id/294/300/200", 1, 1, 1),

            createDish("红烧肉", "主食", new BigDecimal("38.00"),
                "肥而不腻，入口即化，经典美味",
                "https://picsum.photos/id/295/300/200", 1, 1, 1),

            // 荤菜类
            createDish("清蒸鲈鱼", "荤菜", new BigDecimal("68.00"),
                "新鲜鲈鱼清蒸，肉质鲜美，营养丰富",
                "https://picsum.photos/id/296/300/200", 1, 1, 1),

            createDish("白切鸡", "荤菜", new BigDecimal("45.00"),
                "广东名菜，鸡肉嫩滑，配以姜葱酱料",
                "https://picsum.photos/id/297/300/200", 0, 1, 1),

            createDish("蒜蓉粉丝蒸扇贝", "荤菜", new BigDecimal("58.00"),
                "扇贝鲜美，粉丝入味，蒜香浓郁",
                "https://picsum.photos/id/298/300/200", 1, 1, 1),

            createDish("椒盐虾", "荤菜", new BigDecimal("52.00"),
                "虾肉鲜嫩，椒盐香脆，下酒佳品",
                "https://picsum.photos/id/299/300/200", 0, 1, 1),

            // 素菜类
            createDish("上汤娃娃菜", "素菜", new BigDecimal("16.00"),
                "娃娃菜嫩绿，上汤鲜美，清淡爽口",
                "https://picsum.photos/id/300/300/200", 1, 1, 1),

            createDish("蒜蓉西兰花", "素菜", new BigDecimal("14.00"),
                "西兰花翠绿，蒜香浓郁，营养健康",
                "https://picsum.photos/id/301/300/200", 0, 1, 1),

            createDish("干煸四季豆", "素菜", new BigDecimal("18.00"),
                "四季豆干煸，香辣可口，下饭神器",
                "https://picsum.photos/id/302/300/200", 1, 1, 1),

            createDish("醋溜土豆丝", "素菜", new BigDecimal("12.00"),
                "土豆丝爽脆，醋香开胃，经典小菜",
                "https://picsum.photos/id/303/300/200", 0, 1, 1),

            // 汤品类
            createDish("紫菜蛋花汤", "汤品", new BigDecimal("8.00"),
                "紫菜鲜美，蛋花嫩滑，营养丰富",
                "https://picsum.photos/id/304/300/200", 1, 1, 1),

            createDish("番茄蛋汤", "汤品", new BigDecimal("10.00"),
                "番茄酸甜，蛋花嫩滑，开胃暖身",
                "https://picsum.photos/id/305/300/200", 0, 1, 1),

            createDish("冬瓜排骨汤", "汤品", new BigDecimal("25.00"),
                "冬瓜清甜，排骨鲜美，滋补养生",
                "https://picsum.photos/id/306/300/200", 1, 1, 1),

            createDish("玉米排骨汤", "汤品", new BigDecimal("28.00"),
                "玉米香甜，排骨鲜美，营养美味",
                "https://picsum.photos/id/307/300/200", 0, 1, 1),

            // 凉菜类
            createDish("口水鸡", "凉菜", new BigDecimal("35.00"),
                "鸡肉嫩滑，麻辣鲜香，开胃爽口",
                "https://picsum.photos/id/308/300/200", 1, 1, 1),

            createDish("凉拌黄瓜", "凉菜", new BigDecimal("8.00"),
                "黄瓜爽脆，蒜香浓郁，清爽开胃",
                "https://picsum.photos/id/309/300/200", 0, 1, 1),

            createDish("凉拌木耳", "凉菜", new BigDecimal("12.00"),
                "木耳爽脆，酸辣可口，营养丰富",
                "https://picsum.photos/id/310/300/200", 1, 1, 1),

            createDish("凉拌海带丝", "凉菜", new BigDecimal("10.00"),
                "海带丝爽滑，酸辣开胃，健康美味",
                "https://picsum.photos/id/311/300/200", 0, 1, 1),

            // 甜品类
            createDish("红豆沙汤圆", "甜品", new BigDecimal("15.00"),
                "汤圆软糯，红豆香甜，温暖甜蜜",
                "https://picsum.photos/id/312/300/200", 1, 1, 1),

            createDish("银耳莲子羹", "甜品", new BigDecimal("18.00"),
                "银耳滑嫩，莲子香甜，滋补养颜",
                "https://picsum.photos/id/313/300/200", 0, 1, 1),

            createDish("芒果布丁", "甜品", new BigDecimal("12.00"),
                "布丁滑嫩，芒果香甜，清新爽口",
                "https://picsum.photos/id/314/300/200", 1, 1, 1),

            createDish("双皮奶", "甜品", new BigDecimal("16.00"),
                "奶香浓郁，口感细腻，经典甜品",
                "https://picsum.photos/id/315/300/200", 0, 1, 1)
        );

        // 批量保存菜品数据
        dishService.saveBatch(dishes);
        log.info("成功初始化 {} 道菜品", dishes.size());
    }

    /**
     * 创建菜品对象
     */
    private Dish createDish(String name, String category, BigDecimal price,
                           String description, String imageUrl,
                           Integer recommend, Integer stockStatus, Integer restaurantId) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setCategory(category);
        dish.setPrice(price);
        dish.setDescription(description);
        dish.setImageUrl(imageUrl);
        dish.setRecommend(recommend);
        dish.setStockStatus(stockStatus);
        dish.setRestaurantId(restaurantId);
        return dish;
    }
}

