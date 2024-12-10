package com.mcloud.matrix.outstock.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description
 * @Author Miragic
 * @Date 2024/9/22 16:14
 * @Version 1.0
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     *
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public<T> Result<T> bizExceptionHandler(BusinessException e) {
        log.info(e.getMessage());
        return Result.bussinessErr(e);
    }

    /**
     * 处理其他异常
     *
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        log.error(e.toString());
        return Result.otherErr(ResultCode.INTERNAL_SERVER_ERROR);
    }
}
