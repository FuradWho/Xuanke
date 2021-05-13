package com.spoof.xuanke.web.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spoof.xuanke.mapper.CollegeMapper;
import com.spoof.xuanke.pojo.College;
import com.spoof.xuanke.web.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<College> getColleges() {
        return collegeMapper.selectList(null);
    }

    @Override
    public List<College> getCollegeName() {
        QueryWrapper<College> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name");
        return collegeMapper.selectList(queryWrapper);
    }
}
