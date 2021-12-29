package com.google.controller;

import com.google.annotation.Log;
import com.google.service.LogService;
import com.google.service.dto.LogQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author iris
 */
@RestController
@RequestMapping("/api/logs")
@Api(tags = "系统：日志管理")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @PostMapping
    @ApiOperation("查询日志")
    @PreAuthorize("@el.check()")
    public ResponseEntity<Object> query(@RequestBody LogQueryCriteria criteria) {
        return ResponseEntity.ok(logService.query(criteria));
    }

    @GetMapping(value = "/error/{id}")
    @ApiOperation("日志异常详情查询")
    @PreAuthorize("@el.check()")
    public ResponseEntity<Object> queryErrorLogs(@PathVariable Long id) {
        return ResponseEntity.ok(logService.findByErrDetail(id));
    }

    @DeleteMapping(value = "/del/error")
    @Log("删除所有ERROR日志")
    @ApiOperation("删除所有ERROR日志")
    @PreAuthorize("@el.check()")
    public ResponseEntity<Void> delAllErrorLog() {
        logService.delAllByError();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/del/info")
    @Log("删除所有INFO日志")
    @ApiOperation("删除所有INFO日志")
    @PreAuthorize("@el.check()")
    public ResponseEntity<Void> delAllInfoLog() {
        logService.delAllByInfo();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
