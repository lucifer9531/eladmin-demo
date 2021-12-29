package com.google.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author iris
 */
@Getter
@Setter
@NoArgsConstructor
@TableName(value = "sys_log")
public class Log implements Serializable {

    @TableId(value = "log_id", type = IdType.AUTO)
    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;

    @ApiModelProperty(value = "操作用户", hidden = true)
    private String username;

    @ApiModelProperty(value = "描述", hidden = true)
    private String description;

    @ApiModelProperty(value = "方法名")
    private String method;

    @ApiModelProperty(value = "参数", hidden = true)
    private String params;

    @ApiModelProperty(value = "日志类型")
    private String logType;

    @ApiModelProperty(value = "请求Ip", hidden = true)
    private String requestIp;

    @ApiModelProperty(value = "地址", hidden = true)
    private String address;

    @ApiModelProperty(value = "浏览器", hidden = true)
    private String browser;

    @ApiModelProperty(value = "请求耗时", hidden = true)
    private Long time;

    @ApiModelProperty(value = "异常详细", hidden = true)
    private byte[] exceptionDetail;

    @ApiModelProperty(value = "创建日期", hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;

    public Log(String logType, Long time) {
        this.logType = logType;
        this.time = time;
    }
}
