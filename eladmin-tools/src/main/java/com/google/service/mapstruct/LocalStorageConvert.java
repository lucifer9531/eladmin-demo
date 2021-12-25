package com.google.service.mapstruct;

import com.google.base.BaseMapper;
import com.google.domain.LocalStorage;
import com.google.service.dto.LocalStorageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author iris
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalStorageConvert extends BaseMapper<LocalStorageDTO, LocalStorage> {
}
