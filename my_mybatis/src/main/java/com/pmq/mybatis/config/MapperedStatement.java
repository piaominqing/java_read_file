package com.pmq.mybatis.config;

import com.pmq.mybatis.sqlsource.SqlSource;

public class MapperedStatement {
	private String statementId;
	private Class<?> parameterTypeClass;
	private Class<?> resultTypeClass;
	private String statementType;
	private SqlSource sqlSource;
	public MapperedStatement(String statementId, Class<?> parameterTypeClass, Class<?> resultTypeClass,
			String statementType, SqlSource sqlSource) {
		super();
		this.statementId = statementId;
		this.parameterTypeClass = parameterTypeClass;
		this.resultTypeClass = resultTypeClass;
		this.statementType = statementType;
		this.sqlSource = sqlSource;
	}
	public String getStatementId() {
		return statementId;
	}
	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}
	public Class<?> getParameterTypeClass() {
		return parameterTypeClass;
	}
	public void setParameterTypeClass(Class<?> parameterTypeClass) {
		this.parameterTypeClass = parameterTypeClass;
	}
	public Class<?> getResultTypeClass() {
		return resultTypeClass;
	}
	public void setResultTypeClass(Class<?> resultTypeClass) {
		this.resultTypeClass = resultTypeClass;
	}
	public String getStatementType() {
		return statementType;
	}
	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}
	public SqlSource getSqlSource() {
		return sqlSource;
	}
	public void setSqlSource(SqlSource sqlSource) {
		this.sqlSource = sqlSource;
	}
}
