package com.spoof.xuanke.web.controller;


import com.spoof.xuanke.web.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@CrossOrigin
public class testController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/test")
    public Object list(){
        return studentService.studentList();
    }

}
