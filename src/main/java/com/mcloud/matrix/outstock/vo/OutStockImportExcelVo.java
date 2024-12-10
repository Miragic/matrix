package com.mcloud.matrix.outstock.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import cn.afterturn.easypoi.excel.annotation.Excel;
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
public class OutStockImportExcelVo {

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

}
