package com.mcloud.matrix.outstock.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author Miragic
 * @Date 2024/11/30 14:03
 * @Version 1.0
 **/
public interface IOutStockService {
	/**
	 * @Author Miragic
	 * @Description 导入出库单
	 * @Date 2024/11/30 14:05
	 * @Param [request]
	 * @return void
	 **/
	String importOutStockExcel(MultipartHttpServletRequest request, HttpServletResponse response);

	/**
	* @Description:  找出非 kg 的 excel
	* @Param: [request]
	*/
	String findNoKgExcel(MultipartHttpServletRequest request, HttpServletResponse response);

	/** 
	* @Description: 导入非 kg 的 excel
	* @Param: [request]
	*/
	String importNoKgExcel(MultipartHttpServletRequest request);
}
