package com.google.service;

import com.google.domain.CourseField;
import com.google.domain.CourseList;

import java.util.List;

public interface CourseService {
    List<CourseField> queryCourseField();

    List<CourseList> queryCourseList();
}
