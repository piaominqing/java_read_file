package com.pmq.mybatis.sqlsource;

import com.pmq.mybatis.utils.GenericTokenParser;
import com.pmq.mybatis.utils.ParameterMappingTokenHandler;

public class SqlSourceParser {
	public SqlSource parse(String sqlText) {
		ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
		GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", tokenHandler);
		// tokenParser.parse(sqlText)参数是未处理的，返回值是已处理的（没有${}和#{}）
		String sql = tokenParser.parse(sqlText);
		return new StaticSqlSource(sql, tokenHandler.getParameterMappings());
	}
}
