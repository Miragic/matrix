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
import com.mcloud.matrix.outstock.mybatis.entity.TGmOutstockSku;
import com.mcloud.matrix.outstock.mybatis.entity.TGmOutstockSkuExample;
import com.mcloud.matrix.outstock.mybatis.mapper.TGmOutstockSkuMapper;
import com.mcloud.matrix.outstock.service.IOutStockGmService;
import com.mcloud.matrix.outstock.utils.MyExcelUtils;
import com.mcloud.matrix.outstock.vo.GmOutStockExportExcelVo;
import com.mcloud.matrix.outstock.vo.GmOutStockImportExcelVo;
import com.mcloud.matrix.outstock.vo.GmOutStockNoKgExcelVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
public class OutStockGmServiceImpl implements IOutStockGmService {

	private final TGmOutstockSkuMapper gmOutstockSkuMapper;
	@Override
	public String convertOutstockExcel(MultipartHttpServletRequest request, HttpServletResponse response) {
		// 检验数据参数
		MultipartFile file = request.getFile("file");
		if (file == null || file.isEmpty()) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "未获取到上传的 excel");
		}
		try {
			List<GmOutStockImportExcelVo> excelDataDTOS;
			// 解析Excel
			ImportParams params = new ImportParams();
			params.setTitleRows(3);
			params.setNeedSave(true);
			params.setSaveUrl(FileUtil.getTmpDirPath());
			try {
				ExcelImportResult<GmOutStockImportExcelVo> result = ExcelImportUtil.importExcelMore(file.getInputStream(), GmOutStockImportExcelVo.class, params);
				excelDataDTOS = result.getList();
			} catch (Exception e) {
				return "解析excel失败，请使用正确的模板";
			}
			if (CollUtil.isEmpty(excelDataDTOS)) {
				return "导入数据为null，请根据模板进行检查";
			}
			//excel 处理
			List<GmOutStockExportExcelVo> voList = this.convertOutStockExcel(excelDataDTOS);
			MyExcelUtils.exportExcel(response, voList, GmOutStockExportExcelVo.class, "转换后的出库单");

		} catch (Exception e) {
			log.error("读取excel失败，请联系系统管理员", e.getMessage());
			throw e;
		}
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String importHaveKgExcel(MultipartHttpServletRequest request) {
		// 检验数据参数
		MultipartFile file = request.getFile("file");
		if (file == null || file.isEmpty()) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "未获取到上传的 excel");
		}
		try {
			List<GmOutStockNoKgExcelVo> excelDataDTOS = new ArrayList<>();
			// 解析Excel
			ImportParams params = new ImportParams();
			params.setTitleRows(0);
			params.setNeedSave(true);
			params.setSaveUrl(FileUtil.getTmpDirPath());
			try {
				ExcelImportResult<GmOutStockNoKgExcelVo> result = ExcelImportUtil.importExcelMore(file.getInputStream(), GmOutStockNoKgExcelVo.class, params);
				List<GmOutStockNoKgExcelVo> tempList = result.getList().stream()
						.filter(s -> StrUtil.isAllNotBlank(s.getSkuName(), s.getSkuUnit())
								&& ObjectUtil.isAllNotEmpty(s.getManyKg()))
						.collect(Collectors.toList());
				String tempValue = "";
				for(GmOutStockNoKgExcelVo vo: tempList){
					//合并商品类别解析
					String category = vo.getSkuCategory();
					if(StrUtil.isNotBlank(category)){
						tempValue = category;
					}else {
						category = tempValue;
						vo.setSkuCategory(category);
					}
					excelDataDTOS.add(vo);
				}

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

	private void importHaveKgExcel(List<GmOutStockNoKgExcelVo> excelDataDTOS) {
		log.info("出库单 excel 转换-importHaveKgExcel.开始.");
		//获取库内 sku 名称-单位
		Map<String, TGmOutstockSku> skuMap = this.getAllSkuMap();
		List<GmOutStockNoKgExcelVo> haveKgExcelVoList = new ArrayList<>();
		for(GmOutStockNoKgExcelVo excelVo: excelDataDTOS){
			GmOutStockNoKgExcelVo haveKgExcelVo = new GmOutStockNoKgExcelVo();
			BeanUtil.copyProperties(excelVo, haveKgExcelVo);

			String key = excelVo.getSkuCategory() + "-" + excelVo.getSkuName() + "-" + excelVo.getSkuUnit();
			TGmOutstockSku sku = skuMap.get(key);
			if(ObjectUtil.isEmpty(sku)){
				haveKgExcelVoList.add(haveKgExcelVo);
			}
		}
		List<TGmOutstockSku> skuList = new ArrayList<>();
		if(CollUtil.isNotEmpty(haveKgExcelVoList)){
			skuList = BeanUtil.copyToList(haveKgExcelVoList, TGmOutstockSku.class);
			//插入数据库
			skuList.forEach(gmOutstockSkuMapper::insert);
		}
		log.info("光明-出库单 excel 转换.importHaveKgExcel-结束.importHaveKgExcel:{}",haveKgExcelVoList);
	}

	/**
	* @Description: 获取库内商品map
	* @Param: []
	*/
	private Map<String, TGmOutstockSku> getAllSkuMap() {
		Map<String, TGmOutstockSku> skuMap = new HashMap<>();
		List<TGmOutstockSku> skuList = gmOutstockSkuMapper.selectByExample(new TGmOutstockSkuExample());
		if(CollUtil.isNotEmpty(skuList)){
			skuMap = skuList.stream()
					.collect(Collectors.toMap(
							i->i.getSkuCategory() + "-" + i.getSkuName() + "-" + i.getSkuUnit(), // 键
							Function.identity(),     // 值为当前对象
							(a, b) -> a              // 处理重复键时取第一个
					));
		}
		return skuMap;
	}

	private List<GmOutStockExportExcelVo> convertOutStockExcel(List<GmOutStockImportExcelVo> excelDataDTOS) {
		log.info("出库单 excel 转换-开始.");
		//获取库内 sku 名称-单位
		Map<String, TGmOutstockSku> skuMap = this.getAllSkuMap();
		//转换后的结果
		List<GmOutStockExportExcelVo> exportExcelVoList = new ArrayList<>();
		for(GmOutStockImportExcelVo excelVo: excelDataDTOS){
			GmOutStockExportExcelVo exportExcelVo = new GmOutStockExportExcelVo();
			BeanUtil.copyProperties(excelVo, exportExcelVo);

			BigDecimal amount = excelVo.getSkuAmount();
			BigDecimal count = excelVo.getSkuCount();
			//过滤掉单位为 kg 的
			if("kg".equals(excelVo.getSkuUnit())){
				exportExcelVoList.add(exportExcelVo);
				continue;
			}
			if("斤".equals(excelVo.getSkuUnit())){
				//单位为斤 直接处理
				//单位
				exportExcelVo.setConvertedSkuUnit("kg");
				//数量
				exportExcelVo.setConvertedSkuCount(NumberUtil.mul(0.5, count));
				//单价
				exportExcelVo.setConvertedSkuPrice(NumberUtil.div(amount, exportExcelVo.getConvertedSkuCount(),2, RoundingMode.HALF_UP));
				exportExcelVoList.add(exportExcelVo);
				continue;
			}
			String key = excelVo.getSkuCategory() + "-" + excelVo.getSkuName() + "-" + excelVo.getSkuUnit();
			TGmOutstockSku sku = skuMap.get(key);
			if(ObjectUtil.isEmpty(sku)){
				exportExcelVoList.add(exportExcelVo);
				log.info("出库单 excel 转换.未获取到对应商品基本 kg.skuCategory:{},skuName:{},skuUnit:{}", excelVo.getSkuCategory(),excelVo.getSkuName(),excelVo.getSkuUnit());
				continue;
			}
			BigDecimal manyKg = sku.getManyKg();
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
