package com.pmq.mybatis.sqlsource;

import java.util.HashMap;
import java.util.Map;

public class DynamicContext {
	private StringBuilder sb = new StringBuilder();
	private Map<String, Object> bindings = new HashMap<String, Object>();
	public DynamicContext(Object param) {
		super();
		bindings.put("_parameter", param);
	}
	public Map<String, Object> getBindings() {
		return bindings;
	}
	public void appendSql(String sqlText) {
		sb.append(sqlText);
		sb.append(" ");
	}
	public String getSql() {
		return sb.toString();
	}
	
	
}
