package org.deneblingvo.parser;

import org.deneblingvo.deneb.ParserResultable;
import org.w3c.dom.Node;

public class ParserResult implements ParserResultable {

	private Node node;
	
	@Override
	public Node getNode() {
		return this.node;
	}

	public ParserResult (Node node) {
		this.node = node;
	}
}
