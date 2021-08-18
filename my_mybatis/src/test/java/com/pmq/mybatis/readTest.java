package com.pmq.mybatis;

import java.io.InputStream;

import org.dom4j.Document;
import org.junit.Test;

import com.pmq.mybatis.config.Configuration;
import com.pmq.mybatis.config.XMLConfigParser;
import com.pmq.mybatis.sqlsession.SqlSession;
import com.pmq.mybatis.sqlsession.SqlSessionFactory;
import com.pmq.mybatis.sqlsession.SqlSessionFactoryBuilder;
import com.pmq.mybatis.utils.DocumentUtils;
import com.pmq.mybatis.utils.Resources;

public class readTest {
	/**
	 * 通过mysql 读取配置文件 学习 读取xml
	 */
	@Test
	public void readXmlTest() {
		// 指定文件路径
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 获取Document对象
		Document document = DocumentUtils.readDocument(inputStream);
		// 解析Document获取Configuration对象
		XMLConfigParser configParser = new XMLConfigParser();

		// <configuration>
		Configuration configuration = configParser.parse(document.getRootElement());
		System.out.println(configuration);
	}
	@Test
	public void executeTest() {
		// 指定文件路径
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 获取 sqlsesstionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSqlSession();
		User user = sqlSession.selectOne("findUserId", 1);
		System.out.println(user);
	}
}
