package com.google.controller;

import com.google.service.GenConfigService;
import com.google.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author iris
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/generator")
@Api(tags = "系统：代码生成管理")
public class GeneratorController {

    private final GenConfigService genConfigService;
    private final GeneratorService generatorService;

    @ApiOperation("查询数据库数据")
    @GetMapping(value = "/tables/all")
    public ResponseEntity<Object> queryTables() {
        return ResponseEntity.ok(generatorService.queryTables());
    }
}
