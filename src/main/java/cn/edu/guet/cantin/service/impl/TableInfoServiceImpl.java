package cn.edu.guet.cantin.service.impl;

import cn.edu.guet.cantin.domain.TableInfo;
import cn.edu.guet.cantin.mapper.TableInfoMapper;
import cn.edu.guet.cantin.service.TableInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.flywaydb.core.internal.database.base.Table;
import org.springframework.stereotype.Service;

/**
* @author 曜
* @description 针对表【table_info(桌位信息表)】的数据库操作Service实现
* @createDate 2025-08-12 16:05:25
*/
@Service
public class TableInfoServiceImpl extends ServiceImpl<TableInfoMapper, TableInfo>
    implements TableInfoService {

    @Resource
    private TableInfoMapper tableInfoMapper;

    @Override
    public void updateTable(TableInfo tableInfo) {
        // 校验记录是否存在
        if (tableInfoMapper.selectById(tableInfo.getTableId()) == null) {
            throw new RuntimeException("餐桌不存在，无法更新");
        }
        // 执行更新
        int rows = tableInfoMapper.updateById(tableInfo);
        // 校验更新是否成功（极端情况：并发更新导致记录被删除）
        if (rows == 0) {
            throw new RuntimeException("更新失败，记录可能已被删除");
        }
    }
}




