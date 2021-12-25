package com.google.service.dto;

import com.google.base.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author iris
 */
@Data
public class LocalStorageDTO extends BaseDTO implements Serializable {

    private Long id;

    private String realName;

    private String name;

    private String suffix;

    private String type;

    private String size;
}
