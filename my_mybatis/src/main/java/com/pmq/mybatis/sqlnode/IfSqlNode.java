package com.pmq.mybatis.sqlnode;

import com.pmq.mybatis.sqlsource.DynamicContext;
import com.pmq.mybatis.utils.OgnlUtils;

public class IfSqlNode implements SqlNode {
	private String test;
	private SqlNode rootSqlNode;
	public IfSqlNode(String test, SqlNode rootSqlNode) {
		super();
		this.test = test;
		this.rootSqlNode = rootSqlNode;
	}
	@Override
	public void apply(DynamicContext context) {
		boolean evaluateBoolean = OgnlUtils.evaluateBoolean(test, context.getBindings().get("_parameter"));
		if(evaluateBoolean) {
			rootSqlNode.apply(context);
		}
	}

}
