package cn.edu.guet.cantin.mapper;

import cn.edu.guet.cantin.domain.TableInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 曜
* @description 针对表【table_info(桌位信息表)】的数据库操作Mapper
* @createDate 2025-08-12 16:05:25
* @Entity generator.domain.TableInfo
*/
@Mapper
public interface TableInfoMapper extends BaseMapper<TableInfo> {

    /**
     * 获取可用桌位列表
     * @return 可用桌位列表
     */
    List<TableInfo> selectAvailableTables();

    /**
     * 根据桌位类型获取桌位列表
     * @param tableType 桌位类型
     * @return 桌位列表
     */
    List<TableInfo> selectByTableType(@Param("tableType") String tableType);

    /**
     * 根据容纳人数获取桌位列表
     * @param capacity 容纳人数
     * @return 桌位列表
     */
    List<TableInfo> selectByCapacity(@Param("capacity") Integer capacity);

    /**
     * 根据餐厅ID获取桌位列表
     * @param restaurantId 餐厅ID
     * @return 桌位列表
     */
    List<TableInfo> selectByRestaurantId(@Param("restaurantId") Integer restaurantId);

    /**
     * 获取桌位统计信息
     * @return 统计信息
     */
    Object getTableStatistics();
}




