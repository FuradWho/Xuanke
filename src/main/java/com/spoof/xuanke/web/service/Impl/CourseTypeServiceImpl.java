package com.spoof.xuanke.web.service.Impl;

import com.spoof.xuanke.mapper.CourseTypeMapper;
import com.spoof.xuanke.pojo.CourseType;
import com.spoof.xuanke.web.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseTypeServiceImpl implements CourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;


    @Override
    public List<CourseType> getCourseTypeList() {
        return courseTypeMapper.selectList(null);
    }
}
