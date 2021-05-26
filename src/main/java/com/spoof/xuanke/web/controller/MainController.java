package com.spoof.xuanke.web.controller;

import com.spoof.xuanke.mapper.GradeMapper;
import com.spoof.xuanke.pojo.*;
import com.spoof.xuanke.util.ResultUtil;
import com.spoof.xuanke.web.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private MajorService majorService;

    @Autowired
    private GradeService gradeService;


    @RequiresRoles("student")
    @GetMapping("/getCourseList")
    public Object getCourseList(int pagenum, int pagesize) {
        Student student = getStudentBySubject();
        List<Integer> courseId = scService.getCourseIdByStuId(student.getId());

        if (courseId.size() == 0) {
            return ResultUtil.fail("未查询到课程");
        } else {
            List<GetCourse> getCourses = courseService.getCourseByStuId(pagenum, pagesize, courseId);
            Map<String, Object> map = new HashMap<>();
            map.put("courseList", getCourses);
            map.put("total", getCourses.size());
            return ResultUtil.success(map);
        }
    }


    @RequiresRoles("admin")
    @GetMapping("/getProfessionalList")
    public Object getProfessionalList(int pagenum, int pagesize) {
        List<ProfessionalInfo> professionalInfos = majorService.getProfessionalList(pagenum, pagesize);
        int total = majorService.getProfessionalCount();

        if (professionalInfos.size() == 0) {
            return ResultUtil.fail("未查询到专业");
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("professionalList", professionalInfos);
            map.put("total", total);
            return ResultUtil.success(map);
        }
    }



    @RequiresRoles("admin")
    @GetMapping("/getProGradeList")
    public Object getProGradeList(int pagenum, int pagesize) {
        List<GradeInfo> gradeInfos = gradeService.getProGradeList(pagenum, pagesize);
        int total = gradeService.getGradeCount();

        if (gradeInfos.size() == 0) {
            return ResultUtil.fail("未查询到班级");
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("gradeInfos", gradeInfos);
            map.put("total", total);
            return ResultUtil.success(map);
        }
    }

    @RequiresRoles("admin")
    @GetMapping("/getSupCourseList")
    public Object getSupCourseList(int pagenum, int pagesize) {
        List<SupCourseInfo> supCourseInfos = courseService.getSupCourseList(pagenum, pagesize);
        int total = courseService.getCourseCount();

        if (supCourseInfos.size() == 0) {
            return ResultUtil.fail("未查询到班级");
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("supCourseInfos", supCourseInfos);
            map.put("total", total);
            return ResultUtil.success(map);
        }
    }





    @RequiresRoles("student")
    @GetMapping("/getALLCourseList")
    public Object getALLCourseList(int pagenum, int pagesize) {
        Student student = getStudentBySubject();
        List<Integer> courseId = scService.getCourseIdByStuId(student.getId());
        List<GetCourse> getCourses = new ArrayList<>();
        if (courseId.size() == 0) {
            getCourses = courseService.getAllCourse(pagenum, pagesize);
        } else {
            getCourses = courseService.getNewCourse(pagenum, pagesize, courseId);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("courseList", getCourses);
        map.put("total", getCourses.size());

        return ResultUtil.success(map);
    }
    @RequiresRoles("student")
    @DeleteMapping("/removeCourseById")
    public Object removeCourseById(Integer id) {
        Student student = getStudentBySubject();
        int flag = scService.removeCourseById(id, student.getId());
        if (flag == 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.fail("删除失败");
        }
    }
    @RequiresRoles("student")
    @PostMapping("/chooseCourse")
    public Object chooseCourse(@RequestBody String id) {
        System.out.println(id);
        Student student = getStudentBySubject();
        int flag = scService.addCourse(Integer.parseInt(id), student.getId());
        if (flag == 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.fail("选修课程失败");
        }
    }

    @RequiresRoles("teacher")
    @GetMapping("/getTeacherCourseList")
    public Object getTeacherCourseList(int pagenum, int pagesize) {
        Student student = getStudentBySubject();
        List<Course> courseList = courseService.getCourseByTeacherId(pagenum, pagesize, student.getId());
        if (courseList.size() == 0) {
            return ResultUtil.success();
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("courseList", courseList);
            map.put("total", courseList.size());
            return ResultUtil.success(map);
        }

    }
    @RequiresRoles("teacher")
    @DeleteMapping("/removeCourseByTeacherId")
    public Object removeCourseByTeacherId(Integer id) {
        Student student = getStudentBySubject();
        int flag = courseService.removeCourseById(id, student.getId());
        if (flag == 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.fail("删除失败");
        }
    }

    @RequiresRoles("teacher")
    @GetMapping("/getCourseTypeList")
    public Object getCourseTypeList() {

        List<CourseType> courseTypeList = courseTypeService.getCourseTypeList();
        Map<String, Object> map = new HashMap<>();
        map.put("courseType", courseTypeList);
        return ResultUtil.success(map);
    }

    @RequiresRoles("teacher")
    @GetMapping("/getUserInfo")
    public Object getUserInfo() {

        Student student = getStudentBySubject();
        Map<String, Object> map = new HashMap<>();
        map.put("teacherInfo", student);
        return ResultUtil.success(map);
    }
    @RequiresRoles("student")
    @GetMapping("/getUserStuInfo")
    public Object getUserStuInfo() {

        Student student = getStudentBySubject();
        Map<String, Object> map = new HashMap<>();
        map.put("studentInfo", student);
        return ResultUtil.success(map);
    }


    @RequiresRoles("teacher")
    @PostMapping("/handleCourseFrom")
    public Object register(@RequestBody Course course) {

        Student teacher = getStudentBySubject();

        course.setCreateTime(new Date());
        course.setCourseTeacherId(teacher.getId());

        int flag = courseService.addNewCourse(course);

        if (flag == 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.fail("新增课程失败");
        }
    }

    @RequiresRoles("teacher")
    @PostMapping("/handleUserFrom")
    public Object handleUserFrom(@RequestBody UserInfo userInfo) {

        Student teacher = getStudentBySubject();

        teacher.setStuName(userInfo.getUserName());
        teacher.setStuEmail(userInfo.getUserEmail());
        teacher.setStuPhone(userInfo.getUserPhone());
        teacher.setStuSex(userInfo.getUserSex());

        int flag = studentService.updateStudent(teacher);

        if (flag == 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.fail("修改失败");
        }
    }

    @RequiresRoles("teacher")
    @PostMapping("/setRemarks")
    public Object setRemarks(@RequestBody RemarkInfo remarkInfo) {

        Student teacher = getStudentBySubject();
        System.out.println(remarkInfo);

        int flag = courseService.updateRemarks(remarkInfo);

        if (flag == 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.fail("修改失败");
        }
    }

    @RequiresRoles("student")
    @PostMapping("/handleUserStudentFrom")
    public Object handleUserStudentFrom(@RequestBody UserInfo userInfo) {

        Student student = getStudentBySubject();

        student.setStuName(userInfo.getUserName());
        student.setStuEmail(userInfo.getUserEmail());
        student.setStuPhone(userInfo.getUserPhone());
        student.setStuSex(userInfo.getUserSex());

        int flag = studentService.updateStudent(student);

        if (flag == 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.fail("修改失败");
        }
    }
    @RequiresRoles("teacher")
    @GetMapping("/getStudentList")
    public Object getStudentList(int pagenum, int pagesize) {
        Student teacher = getStudentBySubject();
        if (null == teacher) {
            return ResultUtil.fail("未登录");
        } else {
            Set<StudentInfo> studentInfos = courseService.getStudentByTeacherId(teacher.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("studentList", studentInfos);
            return ResultUtil.success(map);
        }
    }


    public Student getStudentBySubject() {

        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                String stuCode = subject.getPrincipal().toString();
                Student student = studentService.findStudentByCode(stuCode);
                return student;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
