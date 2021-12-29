package com.google.service;

import com.google.domain.Log;
import com.google.service.dto.LogQueryCriteria;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author iris
 */
public interface LogService {
    void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, Log log);

    Object query(LogQueryCriteria criteria);
}
