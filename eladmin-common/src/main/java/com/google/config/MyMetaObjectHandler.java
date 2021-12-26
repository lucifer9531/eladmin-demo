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
        this.strictInsertFill(metaObject, "createTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
        this.strictInsertFill(metaObject, "createBy", String.class, StringUtils.isBlank(SecurityUtils.getCurrentUsername()) ? "System" : SecurityUtils.getCurrentUsername());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // TODO 仅第一次生效 数据库值不为null时，并不会触发
        this.strictUpdateFill(metaObject, "updateTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
        this.strictUpdateFill(metaObject, "updateBy", String.class, StringUtils.isBlank(SecurityUtils.getCurrentUsername()) ? "System" : SecurityUtils.getCurrentUsername());
    }
}