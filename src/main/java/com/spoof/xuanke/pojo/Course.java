package com.spoof.xuanke.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName(value = "t_course")
public class Course {

    @TableId(value = "course_id",type = IdType.AUTO)
    private Integer courseId;

    private Integer courseCredit;
    private Integer courseCycle;
    private String courseGrade;
    private String courseName;
    private Date createTime;
    private String remarks;
    private Integer courseTeacherId;
    @TableField(value = "courseType_id")
    private Integer courseTypeId;

}
