package com.mcloud.matrix.outstock.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author Miragic
 * @Date 2024/11/30 13:53
 * @Version 1.0
 **/
@Data
@ApiModel("出入库导入 excelVO")
public class GmOutStockImportExcelVo {

	@Excel(name = "日期")
	@ApiModelProperty("日期")
	private String date;

	@Excel(name = "摘要")
	@ApiModelProperty("摘要")
	private String desc;

	@Excel(name = "类别")
	@ApiModelProperty("类别")
	private String skuCategory;

	@Excel(name = "商品名")
	@ApiModelProperty("商品名")
	private String skuName;

	@Excel(name = "单位")
	@ApiModelProperty("单位")
	private String skuUnit;

	@Excel(name = "数量")
	@ApiModelProperty("数量")
	private BigDecimal skuCount;

	@Excel(name = "单价")
	@ApiModelProperty("单价")
	private BigDecimal skuPrice;

	@Excel(name = "金额")
	@ApiModelProperty("金额")
	private BigDecimal skuAmount;

	@Excel(name = "发料人")
	@ApiModelProperty("发料人")
	private String sendName;

	@Excel(name = "领料人")
	@ApiModelProperty("领料人")
	private String receiveName;

}
