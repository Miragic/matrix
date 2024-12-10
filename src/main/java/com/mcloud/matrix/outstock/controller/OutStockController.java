package com.mcloud.matrix.outstock.controller;

import com.mcloud.matrix.outstock.service.IOutStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mcloud.matrix.outstock.common.Result;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author Miragic
 * @Date 2024/9/22 15:07
 * @Version 1.0
 **/
@Api(value = "出库单相关", tags = "出库单相关")
@RestController
@RequestMapping("/outStock")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OutStockController {
	private final IOutStockService outStockService;

	@ApiOperation("转换出库单")
	@PostMapping("/importOutStockExcel")
	public Result<?> importOutStockExcel(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		return Result.success(outStockService.importOutStockExcel(request, response));
	}

	@ApiOperation("转换出库单-找出非 kg 的 excel")
	@PostMapping("/findNoKgExcel")
	public Result<?> findNoKgExcel(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		return Result.success(outStockService.findNoKgExcel(request, response));
	}

	@ApiOperation("转换出库单-导入非 kg的 excel")
	@PostMapping("/importNoKgExcel")
	public Result<?> importNoKgExcel(MultipartHttpServletRequest request) throws Exception {
		return Result.success(outStockService.importNoKgExcel(request));
	}

}
