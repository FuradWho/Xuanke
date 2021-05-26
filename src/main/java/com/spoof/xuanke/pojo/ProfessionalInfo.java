package com.spoof.xuanke.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProfessionalInfo {

    private int professionalId;
    private String professionalName;
    private Date professionalCreateDate;
    private int collegeId;
    private String collegeName;

}
