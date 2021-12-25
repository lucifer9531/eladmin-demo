package com.google.service.dto;

import com.google.domain.LocalStorage;
import lombok.Data;

/**
 * @author iris
 */
@Data
public class LocalStorageQueryCriteria {
    private Integer pageNum;
    private Integer pageSize;
    private LocalStorage params;
}