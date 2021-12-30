package com.google.controller;

import com.google.domain.GenConfig;
import com.google.service.GenConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author iris
 */
@RestController
@RequestMapping("/api/genConfig")
@RequiredArgsConstructor
@Api(tags = "系统：代码生成器配置管理")
public class GenConfigController {

    private final GenConfigService genConfigService;

    @ApiOperation("查询")
    @GetMapping("/{tableName}")
    public ResponseEntity<Object> query(@PathVariable String tableName) {
        return ResponseEntity.ok(genConfigService.findByName(tableName));
    }

    @PutMapping
    @ApiOperation("修改配置")
    public ResponseEntity<Object> update(@Validated @RequestBody GenConfig genConfig) {
        return ResponseEntity.ok(genConfigService.update(genConfig.getTableName(), genConfig));
    }
}
