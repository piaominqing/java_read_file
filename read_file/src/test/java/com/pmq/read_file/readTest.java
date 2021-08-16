package com.pmq.read_file;

import java.io.InputStream;

import org.dom4j.Document;
import org.junit.Test;

import com.pmq.read_file.utils.DocumentUtils;
import com.pmq.read_file.xml.Configuration;
import com.pmq.read_file.xml.Resources;
import com.pmq.read_file.xml.XMLConfigParser;

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
