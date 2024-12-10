package com.mcloud.matrix.outstock.generator;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Miragic
 * @Date 2024/11/30 12:40
 * @Version 1.0
 **/
public class MapperExtPluginAdapter extends PluginAdapter {

	@Override
	public boolean validate(List<String> warnings) {
		// 校验插件配置是否正确
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		// 获取原始 Mapper 类型
		FullyQualifiedJavaType baseMapperType = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
		String packageName = baseMapperType.getPackageName();
		String baseMapperName = baseMapperType.getShortName();

		// 构造扩展 Mapper 名称
		String extMapperName = baseMapperName + "Ext";
		FullyQualifiedJavaType extMapperType = new FullyQualifiedJavaType(packageName + "." + extMapperName);

		// 创建新的接口
		Interface extMapperInterface = new Interface(extMapperType);
		extMapperInterface.setVisibility(JavaVisibility.PUBLIC);
		extMapperInterface.addSuperInterface(baseMapperType); // 继承原始 Mapper
		extMapperInterface.addImportedType(baseMapperType);   // 导入原始 Mapper 类型

		// 添加 Java 文件的注释
		context.getCommentGenerator().addJavaFileComment(extMapperInterface);

		// 创建 GeneratedJavaFile
		GeneratedJavaFile extMapperFile = new GeneratedJavaFile(
				extMapperInterface,
				context.getJavaClientGeneratorConfiguration().getTargetProject(),
				context.getJavaFormatter()
		);

		// 返回额外生成的文件
		List<GeneratedJavaFile> generatedFiles = new ArrayList<>();
		generatedFiles.add(extMapperFile);
		return generatedFiles;
	}
}
