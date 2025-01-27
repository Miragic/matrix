package com.mcloud.matrix.outstock.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

import java.sql.Types;

/**
 * @Description
 * @Author Miragic
 * @Date 2024/11/27 11:35
 * @Version 1.0
 **/
public class CodeGenerator {

	public static void main(String[] args) {
		FastAutoGenerator.create("url", "username", "password")
				.globalConfig(builder -> {
					builder.author("Miragic") // 设置作者
							.enableSwagger() // 开启 swagger 模式
							.outputDir("/opt/outstock"); // 指定输出目录
				})
				.dataSourceConfig(builder ->
						builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
							int typeCode = metaInfo.getJdbcType().TYPE_CODE;
							if (typeCode == Types.SMALLINT) {
								// 自定义类型转换
								return DbColumnType.INTEGER;
							}
							return typeRegistry.getColumnType(metaInfo);
						})
				)
				.packageConfig(builder ->
						builder.parent("com.mcloud.mybatisplus.samples.generator") // 设置父包名
								.moduleName("system") // 设置父包模块名
								.pathInfo(Collections.singletonMap(OutputFile.xml, "/opt/outstock")) // 设置mapperXml生成路径
				)
				.strategyConfig(builder ->
						builder.addInclude("t_outstock_sku") // 设置需要生成的表名
								.addTablePrefix("t_", "c_") // 设置过滤表前缀
				)
				.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
				.execute();
	}
}
