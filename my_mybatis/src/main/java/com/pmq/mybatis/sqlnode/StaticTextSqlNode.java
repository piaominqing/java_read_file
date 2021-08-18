package com.pmq.mybatis.sqlnode;

import com.pmq.mybatis.sqlsource.DynamicContext;

public class StaticTextSqlNode implements SqlNode {
	private String sqlText;

	public StaticTextSqlNode(String sqlText) {
		super();
		this.sqlText = sqlText;
	}

	@Override
	public void apply(DynamicContext context) {
		context.appendSql(sqlText);
	}

}
