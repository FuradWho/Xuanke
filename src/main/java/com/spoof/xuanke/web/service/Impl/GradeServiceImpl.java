package com.spoof.xuanke.web.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spoof.xuanke.mapper.CollegeMapper;
import com.spoof.xuanke.mapper.GradeMapper;
import com.spoof.xuanke.mapper.MajorMapper;
import com.spoof.xuanke.pojo.*;
import com.spoof.xuanke.web.service.GradeService;
import com.spoof.xuanke.web.service.MajorService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public List<Grade> getGrades() {
        return gradeMapper.selectList(null);
    }

    @Override
    public List<GradeInfo> getProGradeList(int pagenum, int pagesize) {

        Page<Grade> page = new Page<>(pagenum,pagesize);
        List<Grade> grades = gradeMapper.selectPage(page,null).getRecords();

        List<GradeInfo> gradeInfos =new ArrayList<>();

        for (Grade g: grades) {
            GradeInfo gradeInfo =new GradeInfo();
            gradeInfo.setGradeId(g.getId());
            gradeInfo.setGradeCollegeId(g.getCollegeId());
            gradeInfo.setGradeMajorId(g.getMajorId());
            gradeInfo.setGradeCreateDate(g.getCreateTime());
            gradeInfo.setGradeName(g.getName());
            gradeInfo.setGradeClass(g.getClassGrade());

            College college = collegeMapper.selectById(g.getCollegeId());
            gradeInfo.setGradeCollege(college.getCollegeName());

            Major major = majorMapper.selectById(g.getMajorId());
            gradeInfo.setGradeMajor(major.getName());

            gradeInfos.add(gradeInfo);

        }

        return gradeInfos;
    }

    @Override
    public int getGradeCount() {
        return gradeMapper.selectCount(null);
    }
}
