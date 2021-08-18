package com.pmq.mybatis.config;

import org.dom4j.Element;

import com.pmq.mybatis.sqlsource.SqlSource;

public class XMLStatementParser {
	private Configuration configuration;

	public XMLStatementParser(Configuration configuration) {
		super();
		this.configuration = configuration;
	}
	public void parseStatement(Element selectElement, String namespace) {
		// 一个mapperedStatement 对应一个select标签
		
		// sql id
		String id = selectElement.attributeValue("id");
		// 入参类型
		String parameterType = selectElement.attributeValue("parameterType");
		// 通过反射 创建入参对象
		Class<?> parameterTypeClass = resolveClass(parameterType);
		
		// 結果类型
		String resultType = selectElement.attributeValue("resultType");
		// 通过反射 创建結果对象
		Class<?> resultTypeClass = resolveClass(resultType);
		
		String statementType = selectElement.attributeValue("statementType");
		statementType = statementType == null || "".equals(statementType) ? "prepared" : statementType;
		
		// SQLSource 就是封裝了 sql 語句
		// 此时封装的SQL语句是没有进行处理的，什么时候处理呢？
		// 处理时机，就是在SqlSession执行的时候
		// SELECT * FROM user WHERE id = #{id}
		// String sqlText = selectElement.getTextTrim();
		// SqlSource sqlSource = new SqlSource(sqlText);
		
		SqlSource sqlSource = createSqlSource(selectElement);
		String statementId = namespace + "." + id;
		// 此处使用构造方法或者set方法去赋值的话，感觉都不爽
		// 构造参数可能有，可能没有
		// 使用构建者模式改造
		MapperedStatement mapperedStatement = new MapperedStatement(statementId, parameterTypeClass, resultTypeClass, statementType, sqlSource);
		configuration.setMapperedStatement(statementId, mapperedStatement);
		
	}
	private SqlSource createSqlSource(Element selectElement) {
		XMLScriptParser scriptParser = new XMLScriptParser(configuration);
		SqlSource sqlSource = scriptParser.parseScriptNode(selectElement);
		return sqlSource;
	}
	private Class<?> resolveClass(String parameterType) {
		try {
			return Class.forName(parameterType);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
