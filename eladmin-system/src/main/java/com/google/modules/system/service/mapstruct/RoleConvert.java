package com.google.modules.system.service.mapstruct;

import com.google.base.BaseConvert;
import com.google.modules.system.domain.Role;
import com.google.modules.system.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author iris
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleConvert extends BaseConvert<RoleDTO, Role> {
}
