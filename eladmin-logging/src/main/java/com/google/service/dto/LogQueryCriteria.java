package com.google.service.dto;

import com.google.domain.Log;
import lombok.Data;


/**
 * 日志查询类
 * @author iris
 */
@Data
public class LogQueryCriteria {

    private Integer pageNum;
    private Integer pageSize;
    private Log params;
}
