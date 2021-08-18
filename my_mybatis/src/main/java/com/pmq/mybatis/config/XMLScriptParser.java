package com.pmq.mybatis.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import com.pmq.mybatis.sqlnode.IfSqlNode;
import com.pmq.mybatis.sqlnode.MixedSqlNode;
import com.pmq.mybatis.sqlnode.SqlNode;
import com.pmq.mybatis.sqlnode.StaticTextSqlNode;
import com.pmq.mybatis.sqlnode.TextSqlNode;
import com.pmq.mybatis.sqlnode.handler.NodeHandler;
import com.pmq.mybatis.sqlsource.DynamicSqlSource;
import com.pmq.mybatis.sqlsource.RawSqlSource;
import com.pmq.mybatis.sqlsource.SqlSource;

public class XMLScriptParser {
	private Configuration configuration;
	private boolean isDynamic = false;
	private Map<String, NodeHandler> nodeHandlerMap = new HashMap<String, NodeHandler>();

	public XMLScriptParser(Configuration configuration) {
		super();
		this.configuration = configuration;
		initNodeHandlerMap();
		
	} 
	private void initNodeHandlerMap() {
		nodeHandlerMap.put("if", new IfNodeHandler());
		
	}
	public SqlSource parseScriptNode(Element selectElement) {
		// 首先将sql 脚本按照不同的类型， 封装到不同的SQLNode
		MixedSqlNode rootSqlNode = parseDynamicTags(selectElement);
		SqlSource sqlSource = null;
		if(isDynamic) {
			sqlSource = new DynamicSqlSource(rootSqlNode);
		}else {
			sqlSource = new RawSqlSource(rootSqlNode);
		}
		return sqlSource;
	}
	private MixedSqlNode parseDynamicTags(Element selectElement) {
		List<SqlNode> contents = new ArrayList<SqlNode>();
		int nodeCount = selectElement.nodeCount();
		for(int i = 0; i < nodeCount; i++) {
				Node node = selectElement.node(i);
				// 将selectElement 的子节点进行判断
				// 如果是文本类型则封装到TextSqlNode 或者StaticTextSqlNode
				// 如果是动态标签 则 递归封装到...
				if(node instanceof Text) {
					String sqlText = node.getText().trim();
					if(sqlText == null || "".equals(sqlText)) {
						continue;
					}
					TextSqlNode sqlNode = new TextSqlNode(sqlText);
					// 判断文本中是否带有${}
					if(sqlNode.isDynamic()) {
						contents.add(sqlNode);
						isDynamic = true;
					}else {
						contents.add(new StaticTextSqlNode(sqlText));
					}
				}else if(node instanceof Element) {
					// if where foreach 等动态sql子标签
					// 根据标签名称, 封装到不同节点信息
					Element nodeToHandle = (Element) node;
					String nodeName = nodeToHandle.getName().toLowerCase();
					NodeHandler nodeHandle = nodeHandlerMap.get(nodeName);
					nodeHandle.handleNode(nodeToHandle, contents);
					isDynamic = true;
				}
		}
		return new MixedSqlNode(contents);
	}
	public class IfNodeHandler implements NodeHandler {

		@Override
		public void handleNode(Element nodeToHandle, List<SqlNode> targetContents) {
			MixedSqlNode rootSqlNode = parseDynamicTags(nodeToHandle);
			String test = nodeToHandle.attributeValue("test");
			IfSqlNode ifSqlNode = new IfSqlNode(test, rootSqlNode);
			targetContents.add(ifSqlNode);

		}

	}

}
