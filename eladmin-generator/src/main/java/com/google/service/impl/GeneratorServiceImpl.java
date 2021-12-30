package com.google.service.impl;

import com.google.service.GeneratorService;
import org.springframework.stereotype.Service;

/**
 * @author iris
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {
    @Override
    public Object queryTables() {
        // 使用预编译防止sql注入
        String sql = "select table_name ,create_time , engine, table_collation, table_comment from information_schema.tables " +
                "where table_schema = (select database()) " +
                "order by create_time desc";
        return null;
    }
}
