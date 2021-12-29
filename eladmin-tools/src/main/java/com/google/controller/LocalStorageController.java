package com.google.controller;

import com.google.annotation.Log;
import com.google.domain.LocalStorage;
import com.google.exception.BadRequestException;
import com.google.service.LocalStorageService;
import com.google.service.dto.LocalStorageQueryCriteria;
import com.google.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Set;

/**
 * @author iris
 */
@RestController
@Api(tags = "工具：本地存储管理")
@RequestMapping("/api/localStorage")
@RequiredArgsConstructor
public class LocalStorageController {

    private final LocalStorageService localStorageService;

    @Log("存储管理：查询文件")
    @PostMapping
    @PreAuthorize("@el.check('storage:list')")
    @ApiOperation("查询文件")
    public ResponseEntity<Object> query(@RequestBody LocalStorageQueryCriteria criteria) {
        return ResponseEntity.ok(localStorageService.query(criteria));
    }

    @Log("存储管理：修改文件")
    @PutMapping
    @ApiOperation("修改文件")
    @PreAuthorize("@el.check('storage:edit')")
    public ResponseEntity<Void> update(@RequestBody LocalStorage resources) {
        localStorageService.update(resources);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Log("存储管理：上传文件")
    @PostMapping("/pictures")
    @ApiOperation("上传图片")
    @PreAuthorize("@el.check('storage:add')")
    public ResponseEntity<Void> upload(@RequestParam MultipartFile file) {
        // 判断文件是否时图片
        String suffix = FileUtil.getExtensionName(file.getOriginalFilename());
        if (!FileUtil.IMAGE.equals(FileUtil.getFileType(suffix))) {
            throw new BadRequestException("只能上传图片");
        }
        localStorageService.create(null, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("存储管理：多选删除")
    @DeleteMapping
    @ApiOperation("多选删除")
    @PreAuthorize("@el.check('storage:del')")
    public ResponseEntity<Void> delete(@RequestBody Set<Long> ids) {
        localStorageService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
