package com.pmq.mybatis.sqlsource;

import com.pmq.mybatis.sqlnode.SqlNode;

public class DynamicSqlSource implements SqlSource {
	private SqlNode rootSqlNode;
	@Override
	public BoundSql getBoundSql(Object param) {
		//首先调用SqlNode的处理 将动态标签和${} 处理一下
		DynamicContext context = new DynamicContext(param);
		rootSqlNode.apply(context);
		
		//再调用sqlSourceParser 来处理 #{}
		SqlSourceParser sqlSourceParser = new SqlSourceParser();
		SqlSource sqlSource = sqlSourceParser.parse(context.getSql());
		return sqlSource.getBoundSql(param);
	}
	public DynamicSqlSource(SqlNode rootSqlNode) {
		super();
		this.rootSqlNode = rootSqlNode;
	}

}
