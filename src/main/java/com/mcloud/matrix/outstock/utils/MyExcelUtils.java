package com.mcloud.matrix.outstock.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Description excel导出相关
 * @Author Miragic
 * @Date 2024/12/6 13:33
 * @Version 1.0
 **/
@Slf4j
public class MyExcelUtils {

	public static void exportExcel(HttpServletResponse response, List<?> list, Class<?> clazz, String fileName){
		// 设置导出参数
		ExportParams params = new ExportParams(null, fileName);
		// 创建工作簿
		Workbook workbook = ExcelExportUtil.exportExcel(params, clazz, list);
		try{
			// 设置响应头
			fileName = URLEncoder.encode(fileName + ".xlsx", "UTF-8").replace("+", "%20");
			response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + fileName);
			response.setContentType("application/vnd.ms-excel");
			// 输出文件
			workbook.write(response.getOutputStream());
		}catch (Exception e){
			log.error("MyExcelUtils.exportExcel导出异常.e:{}", e.getMessage());
		}finally {
			try {
				workbook.close();
			} catch (IOException e) {
				log.error("MyExcelUtils.exportExcel-关闭workbook异常.e:{}", e.getMessage());
			}
		}
	}
}
