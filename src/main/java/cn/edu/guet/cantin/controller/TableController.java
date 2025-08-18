package cn.edu.guet.cantin.controller;

import cn.edu.guet.cantin.domain.TableInfo;
import cn.edu.guet.cantin.http.HttpResult;
import cn.edu.guet.cantin.service.TableInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 桌位管理控制器
 * 提供桌位的增删改查等操作
 */
@RestController
@RequestMapping("/table")
@CrossOrigin(origins = "*")
public class TableController {

    @Resource
    private TableInfoService tableInfoService;

    /**
     * 获取所有桌位列表
     * @return 桌位列表
     */
    @GetMapping("/list")
    public HttpResult getAllTables() {
        try {
            List<TableInfo> tables = tableInfoService.list();
            return HttpResult.ok(tables);
        } catch (Exception e) {
            return HttpResult.error("获取桌位列表失败: " + e.getMessage());
        }
    }

    /**
     * 分页获取桌位列表
     * @param current 当前页
     * @param size 每页大小
     * @return 分页桌位列表
     */
    @GetMapping("/page")
    public HttpResult getTablePage(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size) {
        try {
            Page<TableInfo> page = new Page<>(current, size);
            Page<TableInfo> result = tableInfoService.page(page);
            return HttpResult.ok(result);
        } catch (Exception e) {
            return HttpResult.error("分页获取桌位失败: " + e.getMessage());
        }
    }

    /**
     * 根据ID获取桌位信息
     * @param id 桌位ID
     * @return 桌位信息
     */
    @GetMapping("/{id}")
    public HttpResult getTableById(@PathVariable Integer id) {
        try {
            TableInfo table = tableInfoService.getById(id);
            if (table != null) {
                return HttpResult.ok(table);
            } else {
                return HttpResult.error("桌位不存在");
            }
        } catch (Exception e) {
            return HttpResult.error("获取桌位信息失败: " + e.getMessage());
        }
    }

    /**
     * 添加新桌位
     * @param tableInfo 桌位信息
     * @return 操作结果
     */
    @PostMapping("/add")
    public HttpResult addTable(@RequestBody TableInfo tableInfo) {
        try {
            // 验证必填字段
            if (tableInfo.getTableNumber() == null || tableInfo.getTableNumber().trim().isEmpty()) {
                return HttpResult.error("桌位编号不能为空");
            }
            if (tableInfo.getCapacity() == null || tableInfo.getCapacity() <= 0) {
                return HttpResult.error("容纳人数必须大于0");
            }
            if (tableInfo.getRestaurantId() == null) {
                return HttpResult.error("餐厅ID不能为空");
            }

            // 检查桌位编号是否已存在
            QueryWrapper<TableInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("table_number", tableInfo.getTableNumber())
                       .eq("restaurant_id", tableInfo.getRestaurantId());
            if (tableInfoService.count(queryWrapper) > 0) {
                return HttpResult.error("该桌位编号已存在");
            }

            // 设置默认状态
            if (tableInfo.getStatus() == null || tableInfo.getStatus().trim().isEmpty()) {
                tableInfo.setStatus("available");
            }

            boolean success = tableInfoService.save(tableInfo);
            if (success) {
                return HttpResult.ok("桌位添加成功", tableInfo);
            } else {
                return HttpResult.error("桌位添加失败");
            }
        } catch (Exception e) {
            return HttpResult.error("添加桌位失败: " + e.getMessage());
        }
    }

    /**
     * 更新桌位信息
     * @param id 桌位ID
     * @param tableInfo 更新的桌位信息
     * @return 操作结果
     */
    @PutMapping("/{id}")
    public HttpResult updateTable(@PathVariable Integer id, @RequestBody TableInfo tableInfo) {
        try {
            // 检查桌位是否存在
            TableInfo existingTable = tableInfoService.getById(id);
            if (existingTable == null) {
                return HttpResult.error("桌位不存在");
            }

            // 设置ID
            tableInfo.setTableId(id);

            // 验证必填字段
            if (tableInfo.getTableNumber() == null || tableInfo.getTableNumber().trim().isEmpty()) {
                return HttpResult.error("桌位编号不能为空");
            }
            if (tableInfo.getCapacity() == null || tableInfo.getCapacity() <= 0) {
                return HttpResult.error("容纳人数必须大于0");
            }

            // 检查桌位编号是否与其他桌位重复
            QueryWrapper<TableInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("table_number", tableInfo.getTableNumber())
                       .eq("restaurant_id", tableInfo.getRestaurantId())
                       .ne("table_id", id);
            if (tableInfoService.count(queryWrapper) > 0) {
                return HttpResult.error("该桌位编号已存在");
            }

            boolean success = tableInfoService.updateById(tableInfo);
            if (success) {
                return HttpResult.ok("桌位更新成功", tableInfo);
            } else {
                return HttpResult.error("桌位更新失败");
            }
        } catch (Exception e) {
            return HttpResult.error("更新桌位失败: " + e.getMessage());
        }
    }

    /**
     * 删除桌位
     * @param id 桌位ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public HttpResult deleteTable(@PathVariable Integer id) {
        try {
            // 检查桌位是否存在
            TableInfo table = tableInfoService.getById(id);
            if (table == null) {
                return HttpResult.error("桌位不存在");
            }

            // 检查桌位状态，如果已被预订或占用则不能删除
            if ("reserved".equals(table.getStatus()) || "occupied".equals(table.getStatus())) {
                return HttpResult.error("该桌位当前状态为" + table.getStatus() + "，无法删除");
            }

            boolean success = tableInfoService.removeById(id);
            if (success) {
                return HttpResult.ok("桌位删除成功");
            } else {
                return HttpResult.error("桌位删除失败");
            }
        } catch (Exception e) {
            return HttpResult.error("删除桌位失败: " + e.getMessage());
        }
    }

    /**
     * 批量删除桌位
     * @param ids 桌位ID列表
     * @return 操作结果
     */
    @DeleteMapping("/batch")
    public HttpResult batchDeleteTables(@RequestBody List<Integer> ids) {
        try {
            if (ids == null || ids.isEmpty()) {
                return HttpResult.error("请选择要删除的桌位");
            }

            // 检查桌位状态
            List<TableInfo> tables = tableInfoService.listByIds(ids);
            for (TableInfo table : tables) {
                if ("reserved".equals(table.getStatus()) || "occupied".equals(table.getStatus())) {
                    return HttpResult.error("桌位 " + table.getTableNumber() + " 当前状态为" + table.getStatus() + "，无法删除");
                }
            }

            boolean success = tableInfoService.removeByIds(ids);
            if (success) {
                return HttpResult.ok("批量删除成功，共删除 " + ids.size() + " 个桌位");
            } else {
                return HttpResult.error("批量删除失败");
            }
        } catch (Exception e) {
            return HttpResult.error("批量删除桌位失败: " + e.getMessage());
        }
    }

    /**
     * 获取可用桌位列表
     * @return 可用桌位列表
     */
    @GetMapping("/available")
    public HttpResult getAvailableTables() {
        try {
            QueryWrapper<TableInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("status", "available");
            List<TableInfo> tables = tableInfoService.list(queryWrapper);
            return HttpResult.ok(tables);
        } catch (Exception e) {
            return HttpResult.error("获取可用桌位失败: " + e.getMessage());
        }
    }

    /**
     * 根据餐厅ID获取桌位列表
     * @param restaurantId 餐厅ID
     * @return 桌位列表
     */
    @GetMapping("/restaurant/{restaurantId}")
    public HttpResult getTablesByRestaurant(@PathVariable Integer restaurantId) {
        try {
            QueryWrapper<TableInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("restaurant_id", restaurantId);
            List<TableInfo> tables = tableInfoService.list(queryWrapper);
            return HttpResult.ok(tables);
        } catch (Exception e) {
            return HttpResult.error("获取餐厅桌位失败: " + e.getMessage());
        }
    }

    /**
     * 根据桌位类型获取桌位列表
     * @param type 桌位类型
     * @return 桌位列表
     */
    @GetMapping("/type/{type}")
    public HttpResult getTablesByType(@PathVariable String type) {
        try {
            QueryWrapper<TableInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("table_type", type);
            List<TableInfo> tables = tableInfoService.list(queryWrapper);
            return HttpResult.ok(tables);
        } catch (Exception e) {
            return HttpResult.error("获取桌位类型失败: " + e.getMessage());
        }
    }

    /**
     * 根据容纳人数获取桌位列表
     * @param capacity 容纳人数
     * @return 桌位列表
     */
    @GetMapping("/capacity/{capacity}")
    public HttpResult getTablesByCapacity(@PathVariable Integer capacity) {
        try {
            QueryWrapper<TableInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.ge("capacity", capacity);
            List<TableInfo> tables = tableInfoService.list(queryWrapper);
            return HttpResult.ok(tables);
        } catch (Exception e) {
            return HttpResult.error("获取桌位容量失败: " + e.getMessage());
        }
    }

    /**
     * 更新桌位状态
     * @param id 桌位ID
     * @param status 新状态
     * @return 操作结果
     */
    @PutMapping("/{id}/status")
    public HttpResult updateTableStatus(@PathVariable Integer id, @RequestParam String status) {
        try {
            // 检查桌位是否存在
            TableInfo table = tableInfoService.getById(id);
            if (table == null) {
                return HttpResult.error("桌位不存在");
            }

            // 验证状态值
            if (!"available".equals(status) && !"reserved".equals(status) && !"occupied".equals(status)) {
                return HttpResult.error("无效的状态值，状态必须是：available、reserved、occupied");
            }

            table.setStatus(status);
            boolean success = tableInfoService.updateById(table);
            if (success) {
                return HttpResult.ok("桌位状态更新成功", table);
            } else {
                return HttpResult.error("桌位状态更新失败");
            }
        } catch (Exception e) {
            return HttpResult.error("更新桌位状态失败: " + e.getMessage());
        }
    }

    /**
     * 搜索桌位
     * @param keyword 搜索关键词
     * @return 搜索结果
     */
    @GetMapping("/search")
    public HttpResult searchTables(@RequestParam String keyword) {
        try {
            QueryWrapper<TableInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("table_number", keyword)
                       .or()
                       .like("table_type", keyword);
            List<TableInfo> tables = tableInfoService.list(queryWrapper);
            return HttpResult.ok(tables);
        } catch (Exception e) {
            return HttpResult.error("搜索桌位失败: " + e.getMessage());
        }
    }
}
