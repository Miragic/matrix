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
public class OutStockExportExcelVo {

	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String skuName;

	@Excel(name = "单位")
	@ApiModelProperty("单位")
	private String skuUnit;

	@Excel(name = "单价")
	@ApiModelProperty("单价")
	private BigDecimal skuPrice;

	@Excel(name = "数量")
	@ApiModelProperty("数量")
	private BigDecimal skuCount;

	@Excel(name = "金额")
	@ApiModelProperty("金额")
	private BigDecimal skuAmount;

	@Excel(name = "备注")
	@ApiModelProperty("备注")
	private String remark;

	@Excel(name = "转换后的单位")
	@ApiModelProperty("转换后的单位")
	private String convertedSkuUnit;

	@Excel(name = "转换后的单价")
	@ApiModelProperty("转换后的单价")
	private BigDecimal convertedSkuPrice;

	@Excel(name = "转换后的数量")
	@ApiModelProperty("转换后的数量")
	private BigDecimal convertedSkuCount;


}
