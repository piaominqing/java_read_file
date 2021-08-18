package com.pmq.mybatis.sqlsource;

import java.util.ArrayList;
import java.util.List;

public class BoundSql {
	private List<ParameterMapping> parameterMappings = new ArrayList<>();
	private String sql;

	public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
		super();
		this.sql = sql;
		this.parameterMappings = parameterMappings;
	}

	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public void setParameterMappings(List<ParameterMapping> parameterMappings) {
		this.parameterMappings = parameterMappings;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	
	
}
