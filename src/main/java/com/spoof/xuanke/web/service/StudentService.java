package com.spoof.xuanke.web.service;

import com.spoof.xuanke.pojo.Student;

import java.util.List;

public interface StudentService {

    List<Student> studentList();

    Student findStudentById(int id);

    int updateStudent(Student student);

    void insertStudent(Student student);

    void deleteStudent(int id);

    boolean isExist(Student student);

    Student findStudentByStu(Student student);

    Student findStudentByCode(String code);

    boolean registerData(Student student , String stuCode, String password);

    String getStuIdByCode(String Code);



}
