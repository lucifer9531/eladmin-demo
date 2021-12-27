package com.google.modules.system.service.dto;

import com.google.base.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author iris
 */
@Data
public class RoleDTO extends BaseDTO implements Serializable {
    private Long id;
    private Set<MenuDTO> menus;
    private String name;
    private String dataScope;
    private Integer level;
    private String description;
}
