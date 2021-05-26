package com.spoof.xuanke.Exceptions;


import com.spoof.xuanke.util.ResultUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
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

    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationException(Exception ex) {
        return "权限不足";
    }

}
