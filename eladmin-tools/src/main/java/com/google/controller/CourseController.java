package com.google.controller;

import com.google.annotation.rest.AnonymousGetMapping;
import com.google.domain.CourseField;
import com.google.domain.CourseList;
import com.google.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author iris
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = "业务：课程切换(redux的使用)")
public class CourseController {

    private final CourseService courseService;

    @AnonymousGetMapping("/course_field")
    @ApiOperation("获取课程分类")
    public ResponseEntity<List<CourseField>> queryCourseField() {
        return ResponseEntity.ok(courseService.queryCourseField());
    }

    @AnonymousGetMapping("/course_list")
    @ApiOperation("获取课程列表")
    public ResponseEntity<List<CourseList>> queryCourseList() {
        return ResponseEntity.ok(courseService.queryCourseList());
    }
}
