package com.google.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.google.utils.SecurityUtils;
import com.google.utils.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author iris
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Timestamp(System.currentTimeMillis()), metaObject);
        this.setFieldValByName("createBy", StringUtils.isBlank(SecurityUtils.getCurrentUsername()) ? "System" : SecurityUtils.getCurrentUsername(), metaObject);
        this.setFieldValByName("updateTime", new Timestamp(System.currentTimeMillis()), metaObject);
        this.setFieldValByName("updateBy", StringUtils.isBlank(SecurityUtils.getCurrentUsername()) ? "System" : SecurityUtils.getCurrentUsername(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Timestamp(System.currentTimeMillis()), metaObject);
        this.setFieldValByName("updateBy", StringUtils.isBlank(SecurityUtils.getCurrentUsername()) ? "System" : SecurityUtils.getCurrentUsername(), metaObject);
    }
}