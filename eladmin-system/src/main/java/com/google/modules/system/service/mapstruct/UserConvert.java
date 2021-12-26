package com.google.modules.system.service.mapstruct;

import com.google.base.BaseConvert;
import com.google.modules.system.service.dto.UserDTO;
import com.google.modules.system.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author iris
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConvert extends BaseConvert<UserDTO, User> {
}
