package com.google.service.impl;

import com.google.domain.CourseField;
import com.google.domain.CourseList;
import com.google.mapper.CourseFieldMapper;
import com.google.mapper.CourseListMapper;
import com.google.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author iris
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseFieldMapper courseFieldMapper;
    private final CourseListMapper courseListMapper;

    @Override
    public List<CourseField> queryCourseField() {
        return courseFieldMapper.selectList(null);
    }

    @Override
    public List<CourseList> queryCourseList() {
        return courseListMapper.selectList(null);
    }
}
