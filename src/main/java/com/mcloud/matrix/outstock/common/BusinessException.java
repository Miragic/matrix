package com.mcloud.matrix.outstock.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author Miragic
 * @Date 2024/9/22 16:13
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException{

    //服务端执行异常的类型
    private String exceptionCatalog;

    // 服务器端回送的服务器运行异常消息
    private String serverExceptionMessage;

    public BusinessException(ResultCode resultCode) {
        this.exceptionCatalog = resultCode.getCode();
        this.serverExceptionMessage = resultCode.getMsg();
    }

}
