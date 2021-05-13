package com.spoof.xuanke.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName(value = "t_college")
public class College {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;


    private Date createTime;
    @TableField(value = "name")
    private String collegeName;
}
