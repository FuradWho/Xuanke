package com.spoof.xuanke.web.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spoof.xuanke.mapper.ScMapper;
import com.spoof.xuanke.pojo.Course;
import com.spoof.xuanke.pojo.Sc;
import com.spoof.xuanke.web.service.ScService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScServiceImpl implements ScService {

    @Autowired
    private ScMapper scMapper;


    @Override
    public List<Integer> getCourseIdByStuId(int id) {

        List<Integer> integers = new ArrayList<>();
        QueryWrapper<Sc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", id);
        List<Sc> sc = scMapper.selectList(queryWrapper);
        for (Sc s : sc) {
            System.out.println(s.getCourseId());
            integers.add(s.getCourseId());
        }
        return integers;
    }

    @Override
    public int removeCourseById(int id, int stuId) {

        QueryWrapper<Sc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        queryWrapper.eq("student_id", stuId);
        queryWrapper.or();
        return scMapper.delete(queryWrapper);

    }

    @Override
    public int addCourse(Integer id, Integer id1) {

        Sc sc = new Sc();
        sc.setCourseId(id);
        sc.setStudentId(id1);
        sc.setCreateTime(new Date());
        sc.setScore(0);

        int flag = scMapper.insert(sc);
        return flag;
    }

    @Override
    public int addNewCourse(Course course) {

        return 0;
    }

}
