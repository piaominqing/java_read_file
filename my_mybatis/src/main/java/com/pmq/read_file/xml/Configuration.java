package com.pmq.read_file.xml;

import javax.sql.DataSource;

public class Configuration {
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String toString() {
		return "Configuration [dataSource=" + dataSource + "]";
	}
	
	
}
