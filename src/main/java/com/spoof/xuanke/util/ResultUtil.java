package com.spoof.xuanke.util;

/**
 * 作用于返回结果
 * @author 13375
 */
public class ResultUtil {

    public static final int SUCCESS_CODE = 0;
    public static final int FAIL_CODE = -1;

    private Integer code;
    private String message;
    private Object data;

    private ResultUtil(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultUtil success() {
        return new ResultUtil(SUCCESS_CODE, null, null);
    }

    public static ResultUtil success(Object data) {
        return new ResultUtil(SUCCESS_CODE, "", data);
    }

    public static ResultUtil fail(String message) {
        return new ResultUtil(FAIL_CODE, message, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
