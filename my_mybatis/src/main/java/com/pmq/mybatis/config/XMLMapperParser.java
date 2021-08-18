package com.pmq.mybatis.config;

import java.util.List;

import org.dom4j.Element;

public class XMLMapperParser {
	private Configuration configuration;

	public XMLMapperParser(Configuration configuration) {
		super();
		this.configuration = configuration;
	};
	
	public void parse(Element rootElement) {
		String namespace = rootElement.attributeValue("namespace");
		// mapper标签下会包含一些sql片段标签， resultMap标签等,这些标签直接解析处理，而statement相关的标签需要单独处理
		// todo 使用XPath语法来进行通配
		List<Element> elements = rootElement.elements("select");
		for(Element selectElement: elements) {
			// select update delete insert 都对应一个statement
			XMLStatementParser statementParser = new XMLStatementParser(configuration);
			statementParser.parseStatement(selectElement, namespace);
		}
		
	}
	
}
