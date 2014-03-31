package org.deneblingvo.serialization.xml.plain;

import java.io.*;
import org.w3c.dom.*;
import java.util.*;

public class Serializer {
	
	private Vector<SerializerItem> items = new Vector<SerializerItem>();

	public Serializer () {
		this.items.add(new SerializerElement());	
	}

	public void serialize (Document document, OutputStream output) throws IOException, NotImplementNodeType {
		String encoding = document.getXmlEncoding();
		if (encoding == null) {
			encoding = "UTF-8";	
		}
		Writer writer = new OutputStreamWriter(output, encoding);
		try {
			writer.write("<?xml version=\"");
			writer.write(document.getXmlVersion());
			writer.write("\" encoding=\"");
			writer.write(encoding);
			writer.write("\" ?>\n");
			NsList list = this.getNsList(document);
			serializeNodes(writer, list, document.getChildNodes(), 0);
		}
		finally {
			writer.close();
		}
	}

	private void serializeNodes(Writer writer, NsList list, NodeList nodes, int deep) throws NotImplementNodeType{
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			serializeNode(writer, list, node, deep);
		}
	}

	private void serializeNode(Writer writer, NsList list, Node node, int deep) throws NotImplementNodeType {
		for (SerializerItem item : this.items) {
			if (item.canSerialize(node)) {
				item.serialize(writer, list, node, deep);
				return;
			}
		}
		throw new NotImplementNodeType();
	}

	private NsList getNsList(Document document) {
		NsList ret = new NsList();
		this.getElementNs(document.getDocumentElement(), ret);
		return ret;
	}

	private void getElementNs(Element element, NsList list) {
		list.addNs(element.getPrefix(), element.getNamespaceURI());
		NamedNodeMap attributes = element.getAttributes();
		for (int i = 0; i < attributes.getLength(); i++) {
			Attr attribute = (Attr) attributes.item(i);
			list.addAttrNs(attribute.getPrefix(), attribute.getNamespaceURI(), element.getNamespaceURI());
		}
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node node = childs.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element child = (Element) node;
				this.getElementNs(child, list);
			}
		}
	}

}
