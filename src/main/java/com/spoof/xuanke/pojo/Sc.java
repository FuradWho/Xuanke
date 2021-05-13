package com.spoof.xuanke.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName(value = "t_sc")
public class Sc {

    @TableId(value = "sc_id",type = IdType.AUTO)
    private Integer id;

    private Integer courseId;
    private Date createTime;
    private Integer score;
    private Integer studentId;

}
