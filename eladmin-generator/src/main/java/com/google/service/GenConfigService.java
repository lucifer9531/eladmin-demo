package com.google.service;

import com.google.domain.GenConfig;

/**
 * @author iris
 */
public interface GenConfigService {
    Object findByName(String tableName);

    Object update(String tableName, GenConfig genConfig);
}
