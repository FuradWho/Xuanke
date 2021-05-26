package com.spoof.xuanke.web.service;

import com.spoof.xuanke.pojo.*;

import java.util.List;
import java.util.Set;

public interface CourseService {

    List<GetCourse> getCourseByStuId(int startIndex, int pageSize, List<Integer> stuId);

    List<GetCourse> getNewCourse(int startIndex, int pageSize, List<Integer> stuId);

    List<Course> getCourseByTeacherId(int startIndex, int pageSize,int teacherId);

    int removeCourseById(Integer id, Integer id1);

    int addNewCourse(Course course);

    Set<StudentInfo> getStudentByTeacherId(Integer id);

    List<GetCourse> getAllCourse(int pagenum, int pagesize);

    int updateRemarks(RemarkInfo remarkInfo);

    List<SupCourseInfo> getSupCourseList(int pagenum, int pagesize);


    int getCourseCount();
}
