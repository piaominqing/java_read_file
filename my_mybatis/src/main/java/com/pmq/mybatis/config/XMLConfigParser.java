package com.pmq.mybatis.config;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import com.pmq.mybatis.utils.DocumentUtils;

/**
 * 解析mybatis 全局配置文件
 * @author nhn
 *
 */
public class XMLConfigParser {
	private Configuration configuration;

	public XMLConfigParser() {
		configuration = new Configuration();
	}
	public Configuration parse(Element rootElement) {
		parseEnvironments(rootElement.element("environments"));
		parseMappers(rootElement.element("mappers"));
		return configuration;
	}
	public void parseEnvironments(Element element) {
		String defaultEnvId = element.attributeValue("default");
		if(defaultEnvId == null || "".equals(defaultEnvId)) {
			return;
		}
		List<Element> elements = element.elements("enviroment");
		for(Element envElement: elements) {
			String envId = envElement.attributeValue("id");
			if(defaultEnvId.equals(envId)) {
				parseEnvironment(envElement);
			}
		}
	}
	public void parseEnvironment(Element envElement) {
		Element dataSourceEnv = envElement.element("dataSource");
		
		String type = dataSourceEnv.attributeValue("type");
		type = type == null || "".equals(type) ? "DBCP" : type;
		if("DBCP".equals(type)) {
			Properties properties = parseProperty(dataSourceEnv);
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(properties.getProperty("driver"));
			dataSource.setUrl(properties.getProperty("url"));
			dataSource.setUsername(properties.getProperty("username"));
			dataSource.setPassword(properties.getProperty("password"));
			
			configuration.setDataSource(dataSource);
		}
	}
	public Properties parseProperty(Element dataSourceEnv) {
		Properties properties = new Properties();
		List<Element> elements = dataSourceEnv.elements("property");
		for(Element element: elements) {
			String name = element.attributeValue("name");
			String value = element.attributeValue("value");
			
			properties.put(name, value);
		}
		return properties;
	}
	public void parseMappers(Element element) {
		List<Element> elements = element.elements("mapper");
		for(Element mapperElement: elements) {
			parseMapper(mapperElement);
		}
	}
	public void parseMapper(Element mapperElement) {
		// 从全局配置文件中获取mapper 路径
		String resource = mapperElement.attributeValue("resource");
		InputStream inputStream = Resources.getResourceAsStream(resource);
		Document document = DocumentUtils.readDocument(inputStream);
		XMLMapperParser mapperParser = new XMLMapperParser(configuration);
		mapperParser.parse(document.getRootElement());
		
	}
}
