package com.google.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author iris
 */
@Data
@TableName("tb_course_list")
public class CourseList implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String field;

    private String courseName;

    private String img;

    private Double price;

    private Integer status;
}
