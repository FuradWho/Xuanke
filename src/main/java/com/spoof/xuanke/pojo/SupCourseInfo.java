package com.spoof.xuanke.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupCourseInfo {

    private int courseId;
    private int courseCredit;
    private int courseCycle;
    private String courseGrade;
    private String courseName;
    private String courseTeacherName;
    private int courseTeacherId;
    private int courseTypeId;
    private String courseTypeName;
    private List<String> StudentCode;
    private int studentCount;

}
