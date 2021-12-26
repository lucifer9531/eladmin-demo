package com.google.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author iris
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@TableName(value = "sys_menu")
public class Menu extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "ID", hidden = true)
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    @ApiModelProperty(value = "权限标识")
    private String permission;
}
