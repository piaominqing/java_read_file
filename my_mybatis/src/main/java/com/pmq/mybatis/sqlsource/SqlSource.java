package com.pmq.mybatis.sqlsource;

public interface SqlSource {
	BoundSql getBoundSql(Object param);
}
