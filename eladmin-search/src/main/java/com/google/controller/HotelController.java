package com.google.controller;

import com.google.service.HotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author iris
 */
@RestController
@RequestMapping("/api/search")
@Api(tags = "工具：ES搜索")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PutMapping
    @ApiOperation("创建索引库")
    @PreAuthorize("@el.check()")
    public ResponseEntity<Void> createIndex(@RequestParam String mappings, @RequestParam String indexName) throws IOException {
        hotelService.createIndex(mappings, indexName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation("测试索引库是否存在")
    @PreAuthorize("@el.check()")
    public ResponseEntity<Boolean> indexIsExist(@RequestParam String indexName) throws IOException {
        return ResponseEntity.ok(hotelService.indexIsExist(indexName));
    }

    @DeleteMapping
    @ApiOperation("删除ES索引库")
    @PreAuthorize("@el.check()")
    public ResponseEntity<Void> deleteIndex(@RequestParam String indexName) throws IOException {
        hotelService.deleteIndex(indexName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
