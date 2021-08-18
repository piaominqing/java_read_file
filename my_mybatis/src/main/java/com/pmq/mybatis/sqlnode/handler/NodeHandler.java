package com.pmq.mybatis.sqlnode.handler;

import java.util.List;

import org.dom4j.Element;

import com.pmq.mybatis.sqlnode.SqlNode;

public interface NodeHandler {
	void handleNode(Element nodeToHandle, List<SqlNode> targetContents);
}
