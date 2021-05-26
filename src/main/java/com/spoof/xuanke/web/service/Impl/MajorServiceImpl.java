package com.spoof.xuanke.web.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spoof.xuanke.mapper.CollegeMapper;
import com.spoof.xuanke.mapper.MajorMapper;
import com.spoof.xuanke.pojo.College;
import com.spoof.xuanke.pojo.Major;
import com.spoof.xuanke.pojo.ProfessionalInfo;
import com.spoof.xuanke.web.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<Major> getMajors() {
        return majorMapper.selectList(null);
    }

    @Override
    public List<ProfessionalInfo> getProfessionalList(int pagenum, int pagesize) {

        Page<Major> page = new Page<>(pagenum,pagesize);
        List<Major> majorList = majorMapper.selectPage(page,null).getRecords();

        List<ProfessionalInfo> professionalInfos =new ArrayList<>();

        for (Major m: majorList) {
            ProfessionalInfo professionalInfo = new ProfessionalInfo();
            professionalInfo.setProfessionalName(m.getName());
            professionalInfo.setProfessionalId(m.getId());
            professionalInfo.setProfessionalCreateDate(m.getCreateTime());
            professionalInfo.setCollegeId(m.getCollegeId());
            College college = collegeMapper.selectById(m.getCollegeId());
            professionalInfo.setCollegeName(college.getCollegeName());
            professionalInfos.add(professionalInfo);
        }
        return professionalInfos;
    }

    @Override
    public int getProfessionalCount() {
        return majorMapper.selectCount(null);
    }
}
