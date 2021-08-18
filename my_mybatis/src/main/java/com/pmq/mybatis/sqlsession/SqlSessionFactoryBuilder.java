package com.pmq.mybatis.sqlsession;

import java.io.InputStream;
import java.io.Reader;

import org.dom4j.Document;

import com.pmq.mybatis.config.Configuration;
import com.pmq.mybatis.config.XMLConfigParser;
import com.pmq.mybatis.utils.DocumentUtils;

/**
 * 使用构建者模式创建 sqlSessionFactory
 * @author nhn
 *
 */
public class SqlSessionFactoryBuilder {

	public SqlSessionFactory build(InputStream inputStream) {
		Document document = DocumentUtils.readDocument(inputStream);
		XMLConfigParser configParser = new XMLConfigParser();
		Configuration configuration = configParser.parse(document.getRootElement());
		return build(configuration);
	}
	private SqlSessionFactory build(Configuration configuration) {
		return new DefaultSqlSessionFactory(configuration);
	}
	public SqlSessionFactory build(Reader reader) {
		return null;
	}
}
