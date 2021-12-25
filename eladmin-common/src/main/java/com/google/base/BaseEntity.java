package com.google.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;

/**
 * 通用字段， is_del 根据需求自行添加
 * @author iris
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人", hidden = true)
    private String createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新人", hidden = true)
    private String updateBy;

    @ApiModelProperty(value = "创建时间", hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;

    /* 分组校验 */
    public @interface Create {}

    /* 分组校验 */
    public @interface Update {}

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                builder.append(f.getName(), f.get(this)).append("\n");
            }
        } catch (Exception e) {
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }
}
