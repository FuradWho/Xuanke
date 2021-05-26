package com.spoof.xuanke.web.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spoof.xuanke.mapper.*;
import com.spoof.xuanke.pojo.*;
import com.spoof.xuanke.web.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ScMapper scMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private CourseTypeMapper courseTypeMapper ;


    @Override
    public List<GetCourse> getCourseByStuId(int startIndex, int pageSize, List<Integer> stuId) {
        List<GetCourse> getCourses = new ArrayList<>();

        for (Integer item : stuId) {
            getCourses.addAll(courseMapper.getCourseByStuId(startIndex, pageSize, item));
        }
        return getCourses;
    }

    @Override
    public List<GetCourse> getNewCourse(int startIndex, int pageSize, List<Integer> stuId) {

        return courseMapper.getNewCourse(startIndex, pageSize, stuId);
    }

    @Override
    public List<Course> getCourseByTeacherId(int startIndex, int pageSize, int teacherId) {
        return courseMapper.getCourseByTeacherId(startIndex, pageSize, teacherId);
    }

    @Override
    public int removeCourseById(Integer id, Integer id1) {

        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        queryWrapper.eq("course_teacher_id", id1);
        queryWrapper.or();
        int flag = courseMapper.delete(queryWrapper);
        if (flag == 1) {
            QueryWrapper<Sc> scQueryWrapper = new QueryWrapper<>();
            scQueryWrapper.eq("course_id", id);
            int res = scMapper.delete(scQueryWrapper);
            if (res == 1) {
                return res;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public int addNewCourse(Course course) {
        return courseMapper.insert(course);
    }

    @Override
    public Set<StudentInfo> getStudentByTeacherId(Integer id) {

        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("course_teacher_id", id);
        List<Course> courses = courseMapper.selectList(courseQueryWrapper);

        List<Sc> scs = new ArrayList<>();
        for (Course c : courses) {
            QueryWrapper<Sc> scQueryWrapper = new QueryWrapper<>();
            scQueryWrapper.eq("course_id", c.getCourseId());
            Sc sc = scMapper.selectOne(scQueryWrapper);
            if (null != sc){
                scs.add(sc);
            }
        }
        if (scs.size() == 0) {
            return null;
        } else {
            Set<Student> students = new HashSet<>();
            for (Sc s : scs) {
                QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
                studentQueryWrapper.eq("stu_id", s.getStudentId());
                Student student = studentMapper.selectOne(studentQueryWrapper);
                if (null != student){
                    students.add(student);
                }
            }

            Set<StudentInfo> studentInfos = new HashSet<>();

            for (Student s : students) {

                StudentInfo studentInfo = new StudentInfo();
                studentInfo.setStudentCode(s.getStuCode());
                studentInfo.setStudentName(s.getStuName());

                College college = collegeMapper.selectById(s.getCollegeId());
                studentInfo.setStudentCollege(college.getCollegeName());

                Grade grade = gradeMapper.selectById(s.getGradeId());
                studentInfo.setStudentGrade(grade.getName());

                Major major = majorMapper.selectById(s.getMajorId());
                studentInfo.setStudentMajor(major.getName());

                studentInfos.add(studentInfo);

            }
            return studentInfos;
        }


    }

    @Override
    public List<GetCourse> getAllCourse(int pagenum, int pagesize) {
        return courseMapper.getAllCourse(pagenum, pagesize);
    }

    @Override
    public int updateRemarks(RemarkInfo remarkInfo) {

        Course course = courseMapper.selectById(remarkInfo.getCourseId());
        course.setRemarks(remarkInfo.getRemarks());
        int flag =  courseMapper.update(course,new QueryWrapper<Course>().eq("course_id",remarkInfo.getCourseId()));
        return flag;
    }

    @Override
    public List<SupCourseInfo> getSupCourseList(int pagenum, int pagesize) {

        Page<Course> page = new Page<>(pagenum,pagesize);
        List<Course> courses = courseMapper.selectPage(page,null).getRecords();

        List<SupCourseInfo> supCourseInfos =new ArrayList<>();

        for (Course c: courses) {

            SupCourseInfo supCourseInfo =new SupCourseInfo();
            supCourseInfo.setCourseId(c.getCourseId());
            supCourseInfo.setCourseCredit(c.getCourseCredit());
            supCourseInfo.setCourseCycle(c.getCourseCycle());
            supCourseInfo.setCourseName(c.getCourseName());
            supCourseInfo.setCourseTeacherId(c.getCourseTeacherId());
            supCourseInfo.setCourseTypeId(c.getCourseTypeId());
            supCourseInfo.setCourseGrade(c.getCourseGrade());

            Student student = studentMapper.selectById(c.getCourseTeacherId());
            supCourseInfo.setCourseTeacherName(student.getStuName());

            CourseType courseType = courseTypeMapper.selectById(c.getCourseTypeId());
            supCourseInfo.setCourseTypeName(courseType.getName());

            List<Sc> scs =scMapper.selectList(new QueryWrapper<Sc>().eq("course_id",c.getCourseId()));

            List<String> stringList =new ArrayList<>();
            if (scs.size() != 0){
                for (Sc s: scs) {
                    Student student1 = studentMapper.selectById(s.getStudentId());
                    stringList.add(student1.getStuCode());
                }
                supCourseInfo.setStudentCode(stringList);
                supCourseInfo.setStudentCount(stringList.size());
            }

            supCourseInfos.add(supCourseInfo);
        }

        return supCourseInfos;
    }

    @Override
    public int getCourseCount() {
        return courseMapper.selectCount(null);
    }

}
