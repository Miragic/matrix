package com.mcloud.matrix.outstock.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description
 * @Author Miragic
 * @Date 2024/9/22 15:11
 * @Version 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    //是否发生服务器端运行时异常
    @Builder.Default
    private Boolean hasServerException = false;

    //服务端执行异常的类型
    private String exceptionCatalog;

    // 服务器端回送的服务器运行异常消息
    private String serverExceptionMessage;

    // 有效的业务回送数据
    private T resultData;

    //自定义异常返回的结果
    public static <T> Result<T> bussinessErr(BusinessException e) {
        Result<T> result = new Result<>();
        result.setHasServerException(true);
        result.setExceptionCatalog(e.getExceptionCatalog());
        result.setServerExceptionMessage(e.getServerExceptionMessage());
        result.setResultData(null);
        return result;
    }
    //其他异常处理方法返回的结果
    public static <T> Result<T> otherErr(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setHasServerException(true);
        result.setExceptionCatalog(resultCode.getCode());
        result.setServerExceptionMessage(resultCode.getMsg());
        result.setResultData(null);
        return result;
    }

    //自定义异常返回的结果
    public static <T> Result<T> success(T t) {
        Result<T> result = new Result<>();
        result.setHasServerException(false);
        result.setResultData(t);
        return result;
    }

    //自定义异常返回的结果
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setHasServerException(false);
        return result;
    }


}
