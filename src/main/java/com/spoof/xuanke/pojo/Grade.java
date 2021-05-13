package com.spoof.xuanke.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName(value = "t_grade")
public class Grade {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String classGrade;
    private Date createTime;
    private String name;
    private Integer collegeId;
    private Integer majorId;

}
