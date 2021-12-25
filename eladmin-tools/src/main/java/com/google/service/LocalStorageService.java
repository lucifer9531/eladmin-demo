package com.google.service;

import com.google.domain.LocalStorage;
import com.google.service.dto.LocalStorageQueryCriteria;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author iris
 */
public interface LocalStorageService {
    Object query(LocalStorageQueryCriteria criteria);

    void create(String name, MultipartFile file);
}
