package com.pmq.read_file.xml;

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
		List<Element> elements = rootElement.elements("select");
		for(Element selectElement: elements) {
			XMLScriptParser scriptParser = new XMLScriptParser(configuration);
			scriptParser.parseScirpt(selectElement);
		}
		
	}
	
}
