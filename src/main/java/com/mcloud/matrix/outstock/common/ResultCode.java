package com.mcloud.matrix.outstock.common;

import java.io.Serializable;

/**
 * @Description
 * @Author Miragic
 * @Date 2024/9/22 16:19
 * @Version 1.0
 **/
public enum ResultCode implements IResultCode, Serializable {

    SUCCESS("200","成功"),
    NOT_FOUND("404","未找到"),
    INTERNAL_SERVER_ERROR("500","服务器内部错误"),
    PARAM_ERROR("900001", "参数有误!"),
    DB_ERROR("900002", "当前数据已被修改,请刷新重试!");

    private String code;
    private String msg;

    ResultCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

