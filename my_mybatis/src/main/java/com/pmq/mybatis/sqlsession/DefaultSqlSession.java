package com.pmq.mybatis.sqlsession;

import java.util.List;

import com.pmq.mybatis.config.Configuration;
import com.pmq.mybatis.config.MapperedStatement;
import com.pmq.mybatis.executor.CachingExecutor;
import com.pmq.mybatis.executor.Executor;
import com.pmq.mybatis.executor.SimpleExecutor;

public class DefaultSqlSession implements SqlSession {
	private Configuration configuration;

	public DefaultSqlSession(Configuration configuration) {
		super();
		this.configuration = configuration;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T selectOne(String statementId, Object param) {
		List<Object> list = this.selectList(statementId, param);
		if(list == null || list.size() == 0) {
			return null;
		}else if(list.size() == 1) {
			return (T) list.get(0);
		}else {
			throw new RuntimeException("只能返回一个对象");
		}
	}

	@Override
	public <T> List<T> selectList(String statementId, Object param) {
		// 根据statementId 获取mapperedStatement对象
		MapperedStatement mapperedStatement = configuration.getMapperedStatement(statementId);
		// 执行statement 的操作（执行方式有俩种： 一种有带有二级缓存的执行方式，一种是基本执行方式【指代有一级缓存，基本执行方式又分成集中：基本执行器，批处理执行器等】）
		// 此处可以考虑放到MappedStatement对象中，该对象中可以根据是否配置了二级缓存来确定创建的是哪个Executor
		Executor executor = new CachingExecutor(new SimpleExecutor());
		return executor.query(mapperedStatement, configuration, param);
	}

}
