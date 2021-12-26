package com.google.modules.system.service.impl;

import com.google.modules.system.domain.Menu;
import com.google.modules.system.domain.Role;
import com.google.modules.system.mapper.MenuMapper;
import com.google.modules.system.mapper.RoleMapper;
import com.google.modules.system.service.RoleService;
import com.google.modules.system.service.dto.UserDTO;
import com.google.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author iris
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "role")
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final MenuMapper menuMapper;

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDTO user) {
        Set<String> permissions = new HashSet<>();
        // 如果是管理员直接返回
        if (user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        Set<Role> roles = roleMapper.findByUserId(user.getId());
        // TODO 可能存在bug
        for (Role role : roles) {
            Set<Menu> menus = menuMapper.findMenuByRoleId(role.getRoleId());
            role.setMenus(menus);
        }
        permissions = roles.stream().flatMap(role -> role.getMenus().stream())
                .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                .map(Menu::getPermission).collect(Collectors.toSet());
        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
