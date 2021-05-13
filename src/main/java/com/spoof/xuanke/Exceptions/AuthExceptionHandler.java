package com.spoof.xuanke.Exceptions;


import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "com.spoof.xuanke.realm")
public class AuthExceptionHandler {


    //==================认证异常====================//

    @ExceptionHandler(ExpiredCredentialsException.class)
    @ResponseBody
    public String expiredCredentialsExceptionHandlerMethod(ExpiredCredentialsException e) {
        return "凭证已过期";
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    @ResponseBody
    public String incorrectCredentialsExceptionHandlerMethod(IncorrectCredentialsException e) {
        return "用户名或密码错误";
    }

    @ExceptionHandler(UnknownAccountException.class)
    @ResponseBody
    public String unknownAccountExceptionHandlerMethod(IncorrectCredentialsException e) {
        return "用户名或密码错误";
    }


    @ExceptionHandler(LockedAccountException.class)
    @ResponseBody
    public String lockedAccountExceptionHandlerMethod(IncorrectCredentialsException e) {
        return "账户被锁定";
    }

    //=================授权异常=====================//

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public String unauthorizedExceptionHandlerMethod(UnauthorizedException e){
        return "未授权！请联系管理员授权";
    }
}
