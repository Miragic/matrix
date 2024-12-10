package com.mcloud.matrix.outstock.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author Miragic
 * @Date 2024/11/30 14:03
 * @Version 1.0
 **/
public interface IOutStockGmService {
	/**
	 * @Author Miragic
	 * @Description 光明-转换出库单
	 * @Date 2024/11/30 14:05
	 * @Param [request]
	 * @return void
	 **/
	String convertOutstockExcel(MultipartHttpServletRequest request, HttpServletResponse response);

	/**
	* @Description: 导入填好多少 kg 的 excel
	* @Param: [request]
	*/
	String importHaveKgExcel(MultipartHttpServletRequest request);
}
