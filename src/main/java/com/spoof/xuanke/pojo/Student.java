package com.spoof.xuanke.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName(value = "t_student")
public class Student implements Serializable {


    @TableId(value = "stu_id")
    private Integer id;

    private Date createTime;
    private String remarks;
    private Integer role;
    private Date stuBirthday;
    private String stuCode;
    private String stuEmail;
    private String stuImg;
    private String stuName;
    private String stuPassword;
    private String stuPhone;
    private Integer stuSex;
    private Integer stuType;
    private Date updateTime;
    private Integer collegeId;
    private Integer gradeId;
    private Integer majorId;
    private String salt;
}
