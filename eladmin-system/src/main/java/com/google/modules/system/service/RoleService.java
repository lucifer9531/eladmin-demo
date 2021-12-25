package com.google.modules.system.service;

import com.google.modules.system.service.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author iris
 */
public interface RoleService {
    /**
     * 获取用户权限信息
     * @param user 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UserDTO user);
}
