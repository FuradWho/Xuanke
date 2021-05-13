package com.spoof.xuanke.web.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spoof.xuanke.mapper.CourseMapper;
import com.spoof.xuanke.mapper.ScMapper;
import com.spoof.xuanke.pojo.Course;
import com.spoof.xuanke.pojo.GetCourse;
import com.spoof.xuanke.pojo.Sc;
import com.spoof.xuanke.pojo.StudentInfo;
import com.spoof.xuanke.web.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ScMapper scMapper;


    @Override
    public List<GetCourse> getCourseByStuId(int startIndex , int pageSize , List<Integer> stuId ) {
        List<GetCourse> getCourses =new ArrayList<>();

        for (Integer item: stuId) {
            getCourses.addAll(courseMapper.getCourseByStuId(startIndex,pageSize,item));
        }
        return getCourses;
    }

    @Override
    public List<GetCourse> getNewCourse(int startIndex, int pageSize, List<Integer> stuId) {

        return courseMapper.getNewCourse(startIndex,pageSize,stuId);
    }

    @Override
    public List<Course> getCourseByTeacherId(int startIndex, int pageSize, int teacherId) {
        return courseMapper.getCourseByTeacherId(startIndex,pageSize,teacherId);
    }

    @Override
    public int removeCourseById(Integer id, Integer id1) {

        QueryWrapper<Course> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        queryWrapper.eq("course_teacher_id",id1);
        queryWrapper.or();
        int flag = courseMapper.delete(queryWrapper);
        if (flag == 1 ){
            QueryWrapper<Sc> scQueryWrapper =new QueryWrapper<>();
            scQueryWrapper.eq("course_id",id);
            int res = scMapper.delete(scQueryWrapper);
            if (res == 1){
                return res;
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }

    @Override
    public int addNewCourse(Course course) {
        return courseMapper.insert(course);
    }

    @Override
    public List<StudentInfo> getStudentByTeacherId(Integer id) {
        return null;
    }
}
