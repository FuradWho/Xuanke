package com.spoof.xuanke.web.service.Impl;


import com.spoof.xuanke.mapper.MajorMapper;
import com.spoof.xuanke.pojo.Major;
import com.spoof.xuanke.web.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public List<Major> getMajors() {
        return majorMapper.selectList(null);
    }
}
