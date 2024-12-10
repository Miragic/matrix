package com.mcloud.matrix.outstock.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.mcloud.matrix.outstock.common.BusinessException;
import com.mcloud.matrix.outstock.common.ResultCode;
import com.mcloud.matrix.outstock.mybatis.entity.TOutstockSku;
import com.mcloud.matrix.outstock.mybatis.entity.TOutstockSkuExample;
import com.mcloud.matrix.outstock.mybatis.mapper.TOutstockSkuMapper;
import com.mcloud.matrix.outstock.service.IOutStockService;
import com.mcloud.matrix.outstock.vo.OutStockExportExcelVo;
import com.mcloud.matrix.outstock.vo.OutStockImportExcelVo;
import com.mcloud.matrix.outstock.vo.OutStockNoKgExcelVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.mcloud.matrix.outstock.utils.MyExcelUtils;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Miragic
 * @Date 2024/11/30 14:03
 * @Version 1.0
 **/
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OutStockServiceImpl implements IOutStockService {

	private final TOutstockSkuMapper outstockSkuMapper;
	@Override
	public String importOutStockExcel(MultipartHttpServletRequest request, HttpServletResponse response) {
		// 检验数据参数
		MultipartFile file = request.getFile("file");
		if (file == null || file.isEmpty()) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "未获取到上传的 excel");
		}
		try {
			List<OutStockImportExcelVo> excelDataDTOS;
			// 解析Excel
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setNeedSave(true);
			params.setSaveUrl(FileUtil.getTmpDirPath());
			try {
				ExcelImportResult<OutStockImportExcelVo> result = ExcelImportUtil.importExcelMore(file.getInputStream(), OutStockImportExcelVo.class, params);
				excelDataDTOS = result.getList().stream()
						.filter(s -> StrUtil.isAllNotBlank(s.getSkuName(), s.getSkuUnit())
								&& ObjectUtil.isAllNotEmpty(s.getSkuPrice(), s.getSkuCount(),s.getSkuAmount()))
						.collect(Collectors.toList());
			} catch (Exception e) {
				return "解析excel失败，请使用正确的模板";
			}
			if (CollUtil.isEmpty(excelDataDTOS)) {
				return "导入数据为null，请根据模板进行检查";
			}
			//excel 处理
			List<OutStockExportExcelVo> voList = this.convertOutStockExcel(excelDataDTOS);
			MyExcelUtils.exportExcel(response, voList, OutStockExportExcelVo.class, "转换后的出库单");

		} catch (Exception e) {
			log.error("读取excel失败，请联系系统管理员", e.getMessage());
			throw e;
		}
		return null;
	}

	@Override
	public String findNoKgExcel(MultipartHttpServletRequest request, HttpServletResponse response) {
		// 检验数据参数
		MultipartFile file = request.getFile("file");
		if (file == null || file.isEmpty()) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "未获取到上传的 excel");
		}
		try {
			List<OutStockImportExcelVo> excelDataDTOS;
			// 解析Excel
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setNeedSave(true);
			params.setSaveUrl(FileUtil.getTmpDirPath());
			try {
				ExcelImportResult<OutStockImportExcelVo> result = ExcelImportUtil.importExcelMore(file.getInputStream(), OutStockImportExcelVo.class, params);
				excelDataDTOS = result.getList().stream()
						.filter(s -> StrUtil.isAllNotBlank(s.getSkuName(), s.getSkuUnit())
								&& ObjectUtil.isAllNotEmpty(s.getSkuPrice(), s.getSkuCount(),s.getSkuAmount()))
						.collect(Collectors.toList());
			} catch (Exception e) {
				return "解析excel失败，请使用正确的模板";
			}
			if (CollUtil.isEmpty(excelDataDTOS)) {
				return "导入数据为null，请根据模板进行检查";
			}
			//excel 处理
			List<OutStockNoKgExcelVo> voList = this.findNoKgExcel(excelDataDTOS);
			MyExcelUtils.exportExcel(response, voList, OutStockNoKgExcelVo.class, "未填多少kg");
		} catch (Exception e) {
			log.error("读取excel失败，请联系系统管理员", e.getMessage());
			throw e;
		}
		return null;
	}

	@Override
	public String importNoKgExcel(MultipartHttpServletRequest request) {
		// 检验数据参数
		MultipartFile file = request.getFile("file");
		if (file == null || file.isEmpty()) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "未获取到上传的 excel");
		}
		try {
			List<OutStockNoKgExcelVo> excelDataDTOS;
			// 解析Excel
			ImportParams params = new ImportParams();
			params.setTitleRows(0);
			params.setNeedSave(true);
			params.setSaveUrl(FileUtil.getTmpDirPath());
			try {
				ExcelImportResult<OutStockNoKgExcelVo> result = ExcelImportUtil.importExcelMore(file.getInputStream(), OutStockNoKgExcelVo.class, params);
				excelDataDTOS = result.getList().stream()
						.filter(s -> StrUtil.isAllNotBlank(s.getSkuName(), s.getSkuUnit())
								&& ObjectUtil.isAllNotEmpty(s.getManyKg()))
						.collect(Collectors.toList());
			} catch (Exception e) {
				return "解析excel失败，请使用正确的模板";
			}
			if (CollUtil.isEmpty(excelDataDTOS)) {
				return "导入数据为null，请根据模板进行检查";
			}
			//excel 处理
			this.importHaveKgExcel(excelDataDTOS);

		} catch (Exception e) {
			log.error("读取excel失败，请联系系统管理员", e.getMessage());
			throw e;
		}
		return null;
	}

	private void importHaveKgExcel(List<OutStockNoKgExcelVo> excelDataDTOS) {
		log.info("出库单 excel 转换-importHaveKgExcel.开始.");
		//获取库内 sku 名称-单位
		Map<String, TOutstockSku> skuMap = new HashMap<>();
		List<TOutstockSku> outstockSkuList = outstockSkuMapper.selectByExample(new TOutstockSkuExample());
		if(CollUtil.isNotEmpty(outstockSkuList)){
			skuMap = outstockSkuList.stream()
					.collect(Collectors.toMap(
							i->i.getSkuName() + "-" + i.getSkuUnit(), // 键为 skuName
							Function.identity(),     // 值为当前对象
							(a, b) -> a              // 处理重复键时取第一个
					));
		}

		List<OutStockNoKgExcelVo> haveKgExcelVoList = new ArrayList<>();
		for(OutStockNoKgExcelVo excelVo: excelDataDTOS){
			OutStockNoKgExcelVo haveKgExcelVo = new OutStockNoKgExcelVo();
			BeanUtil.copyProperties(excelVo, haveKgExcelVo);

			String key = excelVo.getSkuName() + "-" + excelVo.getSkuUnit();
			TOutstockSku sku = skuMap.get(key);
			if(ObjectUtil.isEmpty(sku)){
				haveKgExcelVoList.add(haveKgExcelVo);
			}
		}
		List<TOutstockSku> skuList = new ArrayList<>();
		if(CollUtil.isNotEmpty(haveKgExcelVoList)){
			skuList = BeanUtil.copyToList(haveKgExcelVoList, TOutstockSku.class);
			//插入数据库
			skuList.forEach(outstockSkuMapper::insert);
		}
		log.info("出库单 excel 转换.importHaveKgExcel-结束.importHaveKgExcel:{}",haveKgExcelVoList);
	}

	private List<OutStockNoKgExcelVo> findNoKgExcel(List<OutStockImportExcelVo> excelDataDTOS) {
		log.info("出库单 excel 转换-findNoKgExcel.开始.");
		//获取库内 sku 名称-单位
		Map<String, TOutstockSku> skuMap = new HashMap<>();
		List<TOutstockSku> outstockSkuList = outstockSkuMapper.selectByExample(new TOutstockSkuExample());
		if(CollUtil.isNotEmpty(outstockSkuList)){
			skuMap = outstockSkuList.stream()
					.collect(Collectors.toMap(
							i->i.getSkuName() + "-" + i.getSkuUnit(), // 键为 skuName
							Function.identity(),     // 值为当前对象
							(a, b) -> a              // 处理重复键时取第一个
					));
		}

		List<OutStockNoKgExcelVo> noKgExcelVoList = new ArrayList<>();
		for(OutStockImportExcelVo excelVo: excelDataDTOS){
			OutStockNoKgExcelVo noKgExcelVo = new OutStockNoKgExcelVo();
			BeanUtil.copyProperties(excelVo, noKgExcelVo);
			//过滤掉单位为 kg 的
			if("kg".equals(excelVo.getSkuUnit())){
				continue;
			}
			String key = excelVo.getSkuName() + "-" + excelVo.getSkuUnit();
			TOutstockSku sku = skuMap.get(key);
			if(ObjectUtil.isEmpty(sku)){
				noKgExcelVoList.add(noKgExcelVo);
			}
		}
		log.info("出库单 excel 转换.findNoKgExcel-结束.noKgExcelVo:{}",noKgExcelVoList);
		return noKgExcelVoList;
	}

	private List<OutStockExportExcelVo> convertOutStockExcel(List<OutStockImportExcelVo> excelDataDTOS) {
		log.info("出库单 excel 转换-开始.");
		//获取库内 sku 名称-单位
		Map<String, TOutstockSku> skuMap = new HashMap<>();
		List<TOutstockSku> outstockSkuList = outstockSkuMapper.selectByExample(new TOutstockSkuExample());
		if(CollUtil.isNotEmpty(outstockSkuList)){
			skuMap = outstockSkuList.stream()
							.collect(Collectors.toMap(
									i->i.getSkuName() + "-" + i.getSkuUnit(), // 键为 skuName
									Function.identity(),     // 值为当前对象
									(a, b) -> a              // 处理重复键时取第一个
							));
		}

		List<OutStockExportExcelVo> exportExcelVoList = new ArrayList<>();
		for(OutStockImportExcelVo excelVo: excelDataDTOS){
			OutStockExportExcelVo exportExcelVo = new OutStockExportExcelVo();
			BeanUtil.copyProperties(excelVo, exportExcelVo);
			//过滤掉单位为 kg 的
			if("kg".equals(excelVo.getSkuUnit())){
				exportExcelVoList.add(exportExcelVo);
				continue;
			}
			String key = excelVo.getSkuName() + "-" + excelVo.getSkuUnit();
			TOutstockSku sku = skuMap.get(key);
			if(ObjectUtil.isEmpty(sku)){
				exportExcelVoList.add(exportExcelVo);
				log.info("出库单 excel 转换.未获取到对应商品基本 kg.skuName:{},skuUnit:{}", excelVo.getSkuName(),excelVo.getSkuUnit());
				continue;
			}
			BigDecimal manyKg = sku.getManyKg();
			BigDecimal amount = excelVo.getSkuAmount();
			BigDecimal count = excelVo.getSkuCount();

			//单位
			exportExcelVo.setConvertedSkuUnit("kg");
			//数量
			exportExcelVo.setConvertedSkuCount(NumberUtil.mul(manyKg, count));
			//单价
			exportExcelVo.setConvertedSkuPrice(NumberUtil.div(amount, exportExcelVo.getConvertedSkuCount(),2, RoundingMode.HALF_UP));
			exportExcelVoList.add(exportExcelVo);
		}
		log.info("出库单 excel 转换-结束.exportExcelVoList:{}",exportExcelVoList);
		return exportExcelVoList;
	}
}
