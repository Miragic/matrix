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
public class GmOutStockNoKgExcelVo {

	@Excel(name = "商品类别")
	@ApiModelProperty("商品类别")
	private String skuCategory;

	@Excel(name = "商品名称")
	@ApiModelProperty("名称")
	private String skuName;

	@Excel(name = "商品单位")
	@ApiModelProperty("单位")
	private String skuUnit;

	@Excel(name = "备注")
	@ApiModelProperty("备注")
	private String remark;

	@Excel(name = "多少kg")
	@ApiModelProperty("多少kg")
	private BigDecimal manyKg;

}
