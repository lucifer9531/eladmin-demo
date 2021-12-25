package com.google.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.config.FileProperties;
import com.google.domain.LocalStorage;
import com.google.exception.BadRequestException;
import com.google.mapper.LocalStorageMapper;
import com.google.service.LocalStorageService;
import com.google.service.dto.LocalStorageQueryCriteria;
import com.google.service.mapstruct.LocalStorageConvert;
import com.google.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;

/**
 * @author iris
 */
@Service
@RequiredArgsConstructor
public class LocalStorageServiceImpl implements LocalStorageService {

    private final LocalStorageMapper localStorageMapper;
    private final LocalStorageConvert localStorageConvert;
    private final FileProperties properties;

    @Override
    public Object query(LocalStorageQueryCriteria criteria) {
        Page<LocalStorage> localStoragePage = new Page<>(criteria.getPageNum(), criteria.getPageSize());
        LambdaQueryWrapper<LocalStorage> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(LocalStorage::getName, criteria.getParams().getName())
                .like(LocalStorage::getRealName, criteria.getParams().getRealName());
        Page<LocalStorage> page = localStorageMapper.selectPage(localStoragePage, wrapper);
        return new HashMap<String, Object>(2) {{
            put("total", page.getTotal());
            put("list", page.getRecords());
        }};
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(String name, MultipartFile multipartFile) {
        FileUtil.checkSize(properties.getMaxSize(), multipartFile.getSize());
        String suffix = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        String type = FileUtil.getFileType(suffix);
        File file = FileUtil.upload(multipartFile, properties.getPath().getPath() + type + File.separator);
        if (ObjectUtil.isNull(file)) {
            throw new BadRequestException("上传失败");
        }
        try {
            name = StringUtils.isBlank(name) ? FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename()) : name;
            LocalStorage localStorage = new LocalStorage(
                    file.getName(),
                    name,
                    suffix,
                    file.getPath(),
                    type,
                    FileUtil.getSize(multipartFile.getSize())
            );
            localStorageMapper.insert(localStorage);
        } catch (Exception e) {
            FileUtil.del(file);
            throw e;
        }
    }
}
