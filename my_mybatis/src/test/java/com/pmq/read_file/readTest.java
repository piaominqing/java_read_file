package com.pmq.read_file;

import java.io.InputStream;

import org.dom4j.Document;
import org.junit.Test;

import com.pmq.mybatis.config.Configuration;
import com.pmq.mybatis.config.Resources;
import com.pmq.mybatis.config.XMLConfigParser;
import com.pmq.mybatis.utils.DocumentUtils;

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
}
