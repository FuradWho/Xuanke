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

@TableName(value = "t_major")
public class Major {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Date createTime;
    private String name;
    private Integer collegeId;
}
