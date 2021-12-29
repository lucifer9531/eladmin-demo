package com.google.controller;

import com.google.service.LogService;
import com.google.service.dto.LogQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    // @PreAuthorize("@el.check('log:list')")
    public ResponseEntity<Object> query(@RequestBody LogQueryCriteria criteria) {
        return ResponseEntity.ok(logService.query(criteria));
    }
}
