package com.spoof.xuanke.web.service;

import com.spoof.xuanke.pojo.Course;

import java.util.List;

public interface ScService {

    List<Integer> getCourseIdByStuId(int id);

    int removeCourseById(int id,int stuId);

    int addCourse(Integer id, Integer id1);

    int addNewCourse(Course course);
}
