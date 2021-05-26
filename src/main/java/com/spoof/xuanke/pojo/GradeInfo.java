package com.spoof.xuanke.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeInfo {

    private int GradeId;
    private String gradeClass;
    private Date gradeCreateDate;
    private String gradeName;
    private String gradeCollege;
    private String gradeMajor;
    private int gradeCollegeId;
    private int gradeMajorId;

}
