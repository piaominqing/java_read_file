package com.pmq.mybatis.sqlsession;

import com.pmq.mybatis.config.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
	private Configuration configuration;
	@Override
	public SqlSession openSqlSession() {
		return new DefaultSqlSession(configuration);
	}
	public DefaultSqlSessionFactory(Configuration configuration) {
		super();
		this.configuration = configuration;
	}

}
