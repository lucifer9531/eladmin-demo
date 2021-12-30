package com.google.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.domain.GenConfig;
import com.google.mapper.GenConfigMapper;
import com.google.service.GenConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author iris
 */
@Service
@RequiredArgsConstructor
public class GenConfigServiceImpl implements GenConfigService {

    private final GenConfigMapper genConfigMapper;

    @Override
    public Object findByName(String tableName) {

        LambdaQueryWrapper<GenConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(GenConfig::getTableName, tableName);
        return genConfigMapper.selectList(wrapper);
    }

    @Override
    public Object update(String tableName, GenConfig genConfig) {
        String separator = File.separator;
        String[] paths;
        String symbol = "\\";
        if (symbol.equals(separator)) {
            paths = genConfig.getPath().split("\\\\");
        } else {
            paths = genConfig.getPath().split(File.separator);
        }
        StringBuilder api = new StringBuilder();
        for (String path : paths) {
            api.append(path);
            api.append(separator);
            if ("src".equals(path)) {
                api.append("api");
                break;
            }
        }
        genConfig.setApiPath(api.toString());
        return genConfigMapper.insert(genConfig);
    }
}
