package com.pmq.mybatis.sqlsource;

import com.pmq.mybatis.sqlnode.SqlNode;

public class RawSqlSource implements SqlSource {
	private SqlNode rootSqlNode;
	@Override
	public BoundSql getBoundSql(Object param) {
		// TODO Auto-generated method stub
		return null;
	}
	public RawSqlSource(SqlNode rootSqlNode) {
		super();
		this.rootSqlNode = rootSqlNode;
	}
	

}
