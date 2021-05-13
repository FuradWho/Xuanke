package com.spoof.xuanke.web.service;

import com.spoof.xuanke.pojo.Course;
import com.spoof.xuanke.pojo.GetCourse;
import com.spoof.xuanke.pojo.StudentInfo;

import java.util.List;

public interface CourseService {

    List<GetCourse> getCourseByStuId(int startIndex, int pageSize, List<Integer> stuId);

    List<GetCourse> getNewCourse(int startIndex, int pageSize, List<Integer> stuId);

    List<Course> getCourseByTeacherId(int startIndex, int pageSize,int teacherId);

    int removeCourseById(Integer id, Integer id1);

    int addNewCourse(Course course);

    List<StudentInfo> getStudentByTeacherId(Integer id);
}
