package com.google.modules.system.service.mapstruct;

import com.google.base.BaseConvert;
import com.google.modules.system.domain.Menu;
import com.google.modules.system.service.dto.MenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author iris
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuConvert extends BaseConvert<MenuDTO, Menu> {
}
