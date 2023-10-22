package com.easy.common;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 公共返回对象
 * @author liuxuehan
 * @since 0.0.1
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Number code;
    private String message;
    private Object data;

    /**
     * 成功返回结果
     * @param message
     * @return
     * */
    public static Result success(String message){
        return new Result(ResultCode.CODE_200,message,null);
    }

    /**
     * 成功返回结果
     * @param message
     * @param data
     * @return
     * */
    public static Result success(String message, Object data){
        return new Result(ResultCode.CODE_200,message,data);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     * */
    public static Result error(String message){
        return new Result(ResultCode.CODE_500, message, null);
    }

    /**
     * 失败返回结果
     * @param message
     * @param data
     * @return
     * */
    public static Result error(String message, Object data){
        return new Result(ResultCode.CODE_500,message,data);
    }

    /**
     * 失败返回结果
     * @param code
     * @param message
     * @param data
     * @return
     * */
    public static Result error(Number code,String message, Object data) {
        return new Result(code,message,data);
    }
}
