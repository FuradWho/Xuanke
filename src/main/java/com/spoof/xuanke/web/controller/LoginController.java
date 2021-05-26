package com.spoof.xuanke.web.controller;


import com.spoof.xuanke.pojo.College;
import com.spoof.xuanke.pojo.Grade;
import com.spoof.xuanke.pojo.Major;
import com.spoof.xuanke.pojo.Student;
import com.spoof.xuanke.util.ResultUtil;
import com.spoof.xuanke.web.service.CollegeService;
import com.spoof.xuanke.web.service.GradeService;
import com.spoof.xuanke.web.service.Impl.MenusService;
import com.spoof.xuanke.web.service.MajorService;
import com.spoof.xuanke.web.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private GradeService gradeService;

    private MenusService menusService;

    @PostMapping(value = "/register")
    public Object register(@RequestBody Student student) {
        String stuCode = student.getStuCode();
        String stuPassword = student.getStuPassword();

        stuCode = HtmlUtils.htmlEscape(stuCode);
        student.setStuCode(stuCode);

        boolean flag = studentService.isExist(student);
        if (flag) {
            return ResultUtil.fail("账号已经存在");
        } else {
            studentService.registerData(student, stuCode, stuPassword);
            return ResultUtil.success();
        }
    }

    @GetMapping(value = "/getData")
    public Object getData() {

        List<College> collegeList = collegeService.getColleges();
        List<Grade> gradeList = gradeService.getGrades();
        List<Major> majorList = majorService.getMajors();

        Map<String, Object> map = new HashMap<>();
        map.put("colleges", collegeList);
        map.put("grades", gradeList);
        map.put("majors", majorList);
        return ResultUtil.success(map);
    }

    @PostMapping("/login")
    public Object login(@RequestBody Student student, HttpSession session) {

        boolean flag = studentService.isExist(student);
        System.out.println(student);
        if (flag) {
            try {
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(student.getStuCode(), student.getStuPassword());
                subject.login(token);
                Serializable id = SecurityUtils.getSubject().getSession().getId();
                Map<String, Object> map = new HashMap<>();
                map.put("msg", "登录成功");
                map.put("token", id);
                return ResultUtil.success(map);
            } catch (AuthenticationException e) {
                if (e instanceof UnknownAccountException) {
                    return ResultUtil.fail("用户不存在");
                } else if (e instanceof IncorrectCredentialsException) {
                    return ResultUtil.fail("密码不正确");
                } else if (e instanceof LockedAccountException) {
                    return ResultUtil.fail("用户账号被锁定");
                } else {
                    return ResultUtil.fail("认证失败");
                }
            }
        } else {
            return ResultUtil.fail("账号不存在");
        }
    }

    @PostMapping("/logout")
    public Object logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultUtil.success();
    }

    @GetMapping("/authentication")
    public Object authentication() {
        return ResultUtil.success();
    }


    @GetMapping("/getMenus")
    public Object getMenus(){
        Student student = getStudentBySubiect();
        if (null == student){
            return ResultUtil.fail("未登录");
        }else{
            menusService =new MenusService();
            return ResultUtil.success(menusService.menuList(student.getRole().toString()));
        }
    }

    public Student getStudentBySubiect() {

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
