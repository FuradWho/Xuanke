package com.spoof.xuanke.web.service;

import com.spoof.xuanke.pojo.Grade;
import com.spoof.xuanke.pojo.GradeInfo;

import java.util.List;

public interface GradeService {
    List<Grade> getGrades();

    List<GradeInfo> getProGradeList(int pagenum, int pagesize);

    int getGradeCount();
}
