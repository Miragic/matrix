package com.mcloud.matrix.outstock.common;

public interface IResultCode {

    /**
     * 获取状态码
     * @return  状态码
     */
    String getCode();

    /**
     * 获取状态信息
     * @return  状态信息
     */
    String getMsg();
}
