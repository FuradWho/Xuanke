package com.spoof.xuanke.realm;

import com.spoof.xuanke.pojo.Student;
import com.spoof.xuanke.web.service.StudentService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 13375
 */
public class ShiroRealm extends AuthorizingRealm {


    @Autowired
    @Lazy
    private StudentService studentService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String code = (String) principalCollection.getPrimaryPrincipal();
        System.out.println(code);
        Student student = studentService.findStudentByCode(code);
        System.out.println(student.getRole());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        List<String> permissions = new ArrayList<>();
        List<String> roles = new ArrayList<>();

        if (0 == student.getRole()) {
            roles.add("admin");
            permissions.add("*");
        } else if (1 == student.getRole()) {
            roles.add("student");
            permissions.add("*");
        } else if (2 == student.getRole()) {
            roles.add("teacher");
            permissions.add("*");
        } else {
            return null;
        }

        simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        if(authenticationToken==null){
            return null;
        }
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String stuCode = token.getUsername();
        Student student = studentService.findStudentByCode(stuCode);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(stuCode, student.getStuPassword(), new ShiroByteSource(stuCode),getName());
        return simpleAuthenticationInfo;
    }
}
