package com.pmq.mybatis.executor;

import java.util.List;

import com.pmq.mybatis.config.Configuration;
import com.pmq.mybatis.config.MapperedStatement;

public class CachingExecutor implements Executor {
	private Executor delegate;
	public CachingExecutor(Executor delegate) {
		super();
		this.delegate = delegate;
	}
	@Override
	public <T> List<T> query(MapperedStatement mapperedStatement, Configuration configuration, Object param) {
		// 从二级缓存中根据sql语句获取处理结果（二级缓存怎么存？？？？？）
		// 如果有，则直接返回，如果没有则继续委托给基本执行器去吃力
		delegate.query(mapperedStatement, configuration, param);
		return null;
	}

}
