package com.mcloud.matrix.outstock.controller;

import com.mcloud.matrix.outstock.common.Result;
import com.mcloud.matrix.outstock.service.IOutStockGmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author Miragic
 * @Date 2024/9/22 15:07
 * @Version 1.0
 **/
@Api(value = "出库单相关-光明", tags = "出库单相关-光明")
@RestController
@RequestMapping("/outStock/gm")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OutStockGmController {
	private final IOutStockGmService outStockGmService;

	@ApiOperation("转换出库单")
	@PostMapping("/convertOutstockExcel")
	public Result<?> convertOutstockExcel(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		return Result.success(outStockGmService.convertOutstockExcel(request, response));
	}

	@ApiOperation("转换出库单-导入有 kg 的 excel")
	@PostMapping("/importHaveKgExcel")
	public Result<?> importHaveKgExcel(MultipartHttpServletRequest request) throws Exception {
		return Result.success(outStockGmService.importHaveKgExcel(request));
	}

}
