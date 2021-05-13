package com.spoof.xuanke.web.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spoof.xuanke.mapper.StudentMapper;
import com.spoof.xuanke.pojo.Student;
import com.spoof.xuanke.realm.ShiroByteSource;
import com.spoof.xuanke.web.service.StudentService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class StudentServiceImpl implements StudentService {


    @Autowired
    private StudentMapper studentMapper;


    @Override
    public List<Student> studentList() {
        return studentMapper.selectList(null);
    }

    @Override
    public Student findStudentById(int id) {
        return studentMapper.selectById(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateById(student);
    }

    @Override
    public void insertStudent(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentMapper.deleteById(id);
    }



    @Override
    public boolean isExist(Student student) {

        Student stu = findStudentByStu(student);
        if (null == stu){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Student findStudentByStu(Student student) {

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_code",student.getStuCode());
        Student stu = studentMapper.selectOne(queryWrapper);
        return stu;
    }


    @Override
    public Student findStudentByCode(String code) {

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_code",code);
        Student stu = studentMapper.selectOne(queryWrapper);
        return stu;
    }

    @Override
    public boolean registerData(Student student , String stuCode, String password) {
        String newPs = new SimpleHash("MD5", password, new ShiroByteSource(stuCode), 1024).toHex();
        student.setId(91);
        student.setSalt(stuCode);
        student.setCreateTime(new Date());
        student.setStuPassword(newPs);
        insertStudent(student);
        return true;

    }

    @Override
    public String getStuIdByCode(String Code) {
        return null;
    }


}
