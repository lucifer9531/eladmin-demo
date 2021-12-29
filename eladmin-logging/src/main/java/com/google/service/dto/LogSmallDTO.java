package com.google.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author iris
 */
@Data
public class LogSmallDTO implements Serializable {

    private Long id;
    private String username;
    private String description;
    private String method;
    private String params;
    private String logType;
    private String requestIp;
    private String address;
    private String browser;
    private Long time;
    private byte[] exceptionDetail;
    private Timestamp createTime;
}
