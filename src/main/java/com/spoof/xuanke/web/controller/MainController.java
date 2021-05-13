package com.spoof.xuanke.web.controller;

import com.spoof.xuanke.pojo.*;
import com.spoof.xuanke.util.ResultUtil;
import com.spoof.xuanke.web.service.CourseService;
import com.spoof.xuanke.web.service.CourseTypeService;
import com.spoof.xuanke.web.service.ScService;
import com.spoof.xuanke.web.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.*;

@RestController
@CrossOrigin
public class MainController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ScService scService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping("/getCourseList")
    public Object getCourseList(int pagenum , int pagesize){
        Student student =  getStudentBySubiect();
        List<Integer> courseId = scService.getCourseIdByStuId(student.getId());

        if(courseId.size() == 0){
            return ResultUtil.success();
        }else{
            List<GetCourse> getCourses =courseService.getCourseByStuId(pagenum,pagesize,courseId);
            Map<String,Object> map =new HashMap<>();
            map.put("courseList",getCourses);
            map.put("total",getCourses.size());
            return ResultUtil.success(map);
        }
    }

    @GetMapping("/getALLCourseList")
    public Object getALLCourseList(int pagenum , int pagesize){
        Student student =  getStudentBySubiect();
        List<Integer> courseId = scService.getCourseIdByStuId(student.getId());
        List<GetCourse> getCourses =courseService.getNewCourse(pagenum,pagesize,courseId);
        Map<String,Object> map =new HashMap<>();
        map.put("courseList",getCourses);
        map.put("total",getCourses.size());

        return ResultUtil.success(map);
    }

    @DeleteMapping("/removeCourseById")
    public Object removeCourseById(Integer id){
        Student student =  getStudentBySubiect();
        int flag = scService.removeCourseById(id,student.getId());
        if (flag == 1){
            return ResultUtil.success();
        }else {
            return ResultUtil.fail("删除失败");
        }
    }

    @PostMapping("/chooseCourse")
    public Object chooseCourse(@RequestBody String id){
        System.out.println(id);
        Student student =  getStudentBySubiect();
        int flag = scService.addCourse(Integer.parseInt(id),student.getId());
        if(flag == 1 ){
            return ResultUtil.success();
        }else{
            return ResultUtil.fail("选修课程失败");
        }
    }

    @GetMapping("/getTeacherCourseList")
    public Object getTeacherCourseList(int pagenum , int pagesize){
        Student student =  getStudentBySubiect();
        List<Course> courseList = courseService.getCourseByTeacherId(pagenum,pagesize,student.getId());
        if(courseList.size() == 0){
            return ResultUtil.success();
        }else{
            Map<String,Object> map =new HashMap<>();
            map.put("courseList",courseList);
            map.put("total",courseList.size());
            return ResultUtil.success(map);
        }

    }

    @DeleteMapping("/removeCourseByTeacherId")
    public Object removeCourseByTeacherId(Integer id){
        Student student =  getStudentBySubiect();
        int flag = courseService.removeCourseById(id,student.getId());
        if (flag == 1){
            return ResultUtil.success();
        }else {
            return ResultUtil.fail("删除失败");
        }
    }


    @GetMapping("/getCourseTypeList")
    public Object getCourseTypeList(){

        List<CourseType> courseTypeList = courseTypeService.getCourseTypeList();
        Map<String,Object> map =new HashMap<>();
        map.put("courseType",courseTypeList);
        return ResultUtil.success(map);

    }


    @PostMapping("/handleCourseFrom")
    public Object register(@RequestBody Course course) {

        Student teacher =getStudentBySubiect();

        course.setCreateTime(new Date());
        course.setCourseTeacherId(teacher.getId());

        int flag = courseService.addNewCourse(course);

        if (flag == 1){
            return ResultUtil.success();
        }else{
            return ResultUtil.fail("新增课程失败");
        }
    }

    @GetMapping("/getStudentList")
    public Object getStudentList(int pagenum , int pagesize){
        Student teacher =  getStudentBySubiect();



        List<StudentInfo> studentInfos = courseService.getStudentByTeacherId(teacher.getId());

        return studentInfos;


    }




    public Student getStudentBySubiect(){

        try{
            Subject subject =SecurityUtils.getSubject();
            String stuCode = subject.getPrincipal().toString();
            Student student =studentService.findStudentByCode(stuCode);
            return student;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }




}
