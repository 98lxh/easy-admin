package com.easy.exception;

import lombok.Getter;

/**
 * 自定义异常
 * @author liuxuehan
 * @since 0.0.1
 * */

@Getter
public class ServiceException extends RuntimeException{
    private Integer code;

    public ServiceException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
