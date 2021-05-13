package com.spoof.xuanke.web.service.Impl;

import com.spoof.xuanke.mapper.GradeMapper;
import com.spoof.xuanke.pojo.Grade;
import com.spoof.xuanke.web.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Grade> getGrades() {
        return gradeMapper.selectList(null);
    }
}
