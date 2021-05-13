package com.spoof.xuanke.web.service.Impl;

import com.spoof.xuanke.pojo.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 13375
 */
public class MenusService {

    public List<Menu> menuList(String role){
        List<Menu> list =new ArrayList<>();

        if ("0".equals(role)){

            Menu menu1 = new Menu();
            menu1.setId(10);
            menu1.setAuthName("专业管理");
            menu1.setPath("ProfessionalManagement");


            Menu menu2 = new Menu();
            menu2.setId(11);
            menu2.setAuthName("班级管理");
            menu2.setPath("ClassManagement");

            Menu menu3 = new Menu();
            menu3.setId(12);
            menu3.setAuthName("课程管理");
            menu3.setPath("CourseManagement");

            List<Menu>  list1 =new ArrayList<>();
            list1.add(menu1);
            list1.add(menu2);
            list1.add(menu3);

            Menu menu =new Menu();
            menu.setId(1);
            menu.setAuthName("院系管理");
            menu.setPath("DepartmentManagement");
            menu.setChildren(list1);

            Menu menu4 = new Menu();
            menu4.setId(2);
            menu4.setAuthName("个人管理");
            menu4.setPath("PersonalManagement");

            list.add(menu);
            list.add(menu4);

        }
        if ("1".equals(role)){

            Menu menu1 = new Menu();
            menu1.setId(1);
            menu1.setAuthName("个人信息");
            menu1.setPath("PersonalInformation");

            List<Menu> list1 =new ArrayList<>();
            Menu menu3 =new Menu();
            menu3.setId(10);
            menu3.setAuthName("详情页面");
            menu3.setPath("PersonalInformation");
            list1.add(menu3);

            menu1.setChildren(list1);

            Menu menu2 = new Menu();
            menu2.setId(2);
            menu2.setAuthName("课程信息");
            menu2.setPath("CourseInformation");

            List<Menu> list2 =new ArrayList<>();
            Menu menu4 =new Menu();
            menu4.setId(11);
            menu4.setAuthName("已选修课程");
            menu4.setPath("CourseInformation");
            list2.add(menu4);

            Menu menu5 =new Menu();
            menu5.setId(12);
            menu5.setAuthName("可选修课程");
            menu5.setPath("NewCourseInformation");
            list2.add(menu5);

            menu2.setChildren(list2);

            list.add(menu1);
            list.add(menu2);


        }
        if ("2".equals(role)){

            Menu menu1 = new Menu();
            menu1.setId(1);
            menu1.setAuthName("个人信息");
            menu1.setPath("TeacherPersonalInformation");

            List<Menu> list1 =new ArrayList<>();
            Menu menu3 =new Menu();
            menu3.setId(10);
            menu3.setAuthName("详情页面");
            menu3.setPath("TeacherPersonalInformation");
            list1.add(menu3);

            menu1.setChildren(list1);

            Menu menu2 = new Menu();
            menu2.setId(2);
            menu2.setAuthName("课程信息");
            menu2.setPath("TeacherCourseInformation");

            List<Menu> list2 =new ArrayList<>();
            Menu menu4 =new Menu();
            menu4.setId(11);
            menu4.setAuthName("已开课程");
            menu4.setPath("TeacherCourseInformation");

            Menu menu5 =new Menu();
            menu5.setId(12);
            menu5.setAuthName("课程管理");
            menu5.setPath("TeacherCourseManage");

            Menu menu6 =new Menu();
            menu6.setId(13);
            menu6.setAuthName("新增课程");
            menu6.setPath("TeacherAddCourse");

            list2.add(menu5);
            list2.add(menu6);
            list2.add(menu4);

            menu2.setChildren(list2);

            list.add(menu1);
            list.add(menu2);
        }

        return list;
    }




}
