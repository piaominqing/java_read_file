package com.pmq.mybatis.executor;

import java.util.List;

import com.pmq.mybatis.config.Configuration;
import com.pmq.mybatis.config.MapperedStatement;

public interface Executor {
	<T> List<T> query(MapperedStatement mapperedStatement, Configuration configuration, Object param);
}
