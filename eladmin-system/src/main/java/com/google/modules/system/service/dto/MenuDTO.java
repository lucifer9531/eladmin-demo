package com.google.modules.system.service.dto;

import com.google.base.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author iris
 */
@Data
public class MenuDTO extends BaseDTO implements Serializable {

    private Long id;
    private String permission;
}
