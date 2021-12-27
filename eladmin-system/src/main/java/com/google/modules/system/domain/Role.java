package com.google.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.base.BaseEntity;
import com.google.utils.enums.DataScopeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

/**
 * 角色
 * @author iris
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName(value = "sys_role")
public class Role extends BaseEntity implements Serializable {

    @TableId(value = "role_id", type = IdType.AUTO)
    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;

    @ApiModelProperty(value = "菜单", hidden = true)
    @TableField(exist = false)
    private Set<Menu> menus;

    @NotBlank
    @ApiModelProperty(value = "名称", hidden = true)
    private String name;

    @ApiModelProperty(value = "数据权限，全部 、 本级 、 自定义")
    private String dataScope = DataScopeEnum.THIS_LEVEL.getValue();

    @ApiModelProperty(value = "级别，数值越小，级别越大")
    private Integer level = 3;

    @ApiModelProperty(value = "描述")
    private String description;
}
