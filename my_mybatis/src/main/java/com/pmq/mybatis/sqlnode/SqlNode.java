package com.pmq.mybatis.sqlnode;

import com.pmq.mybatis.sqlsource.DynamicContext;

public interface SqlNode {
	void apply(DynamicContext context);
}
