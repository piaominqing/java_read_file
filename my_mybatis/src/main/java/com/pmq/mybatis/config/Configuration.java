package com.pmq.mybatis.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

public class Configuration {
	private DataSource dataSource;
	private Map<String, MapperedStatement> mapperedStatements = new HashMap<String, MapperedStatement>();
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setMapperedStatement(String statementId, MapperedStatement mapperedStatement) {
		mapperedStatements.put(statementId, mapperedStatement);
	}
	public MapperedStatement getMapperedStatement(String statementId) {
		return mapperedStatements.get(statementId);
	}
	
	
}
