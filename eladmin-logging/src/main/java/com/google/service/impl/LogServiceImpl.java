package com.google.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.domain.Log;
import com.google.mapper.LogMapper;
import com.google.service.LogService;
import com.google.service.dto.LogQueryCriteria;
import com.google.utils.SecurityUtils;
import com.google.utils.StringUtils;
import com.google.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author iris
 */
@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogMapper logMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, Log log) {
        if (log == null) {
            throw new IllegalArgumentException("Log 不能为 null!");
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.google.annotation.Log aopLog = method.getAnnotation(com.google.annotation.Log.class);
        
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        
        // 描述
        log.setDescription(aopLog.value());
        
        log.setRequestIp(ip);
        log.setAddress(StringUtils.getCityInfo(log.getRequestIp()));
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(getParameter(method, joinPoint.getArgs()));
        log.setBrowser(browser);
        logMapper.insert(log);
    }

    @Override
    public Object query(LogQueryCriteria criteria) {
        Page<Log> page = new Page<>(criteria.getPageNum(), criteria.getPageSize());
        LambdaQueryWrapper<Log> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(criteria.getParams().getMethod()), Log::getMethod, criteria.getParams().getMethod())
                .eq(StringUtils.isNotBlank(criteria.getParams().getLogType()), Log::getLogType, criteria.getParams().getLogType())
                .like(Log::getUsername, SecurityUtils.getCurrentUsername());
        Page<Log> logPage = logMapper.selectPage(page, wrapper);
        return new HashMap<String, Object>(2) {{
            put("total", logPage.getTotal());
            put("list", logPage.getRecords());
        }};
    }

    @Override
    public Object findByErrDetail(Long id) {
        Log log = logMapper.selectById(id);
        ValidationUtil.isNull(log.getId(), "Log", "id", id);
        byte[] details = log.getExceptionDetail();
        return Dict.create().set("exception", new String(ObjectUtil.isNotNull(details) ? details : "".getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAllByError() {
        LambdaQueryWrapper<Log> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Log::getLogType, "ERROR");
        logMapper.delete(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAllByInfo() {
        LambdaQueryWrapper<Log> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Log::getLogType, "INFO");
        logMapper.delete(wrapper);
    }

    private String getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            // 将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            // 将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.isEmpty()) {
            return  "";
        }
        return argList.size() == 1 ? JSONUtil.toJsonStr(argList.get(0)) : JSONUtil.toJsonStr(argList);
    }
}
