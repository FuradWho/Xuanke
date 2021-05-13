package com.spoof.xuanke.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfo {

    private int studentId;
    private String studentCode;
    private String studentName;
    private String studentCollege;
    private String studentMajor;
    private String studentGrade;
}
