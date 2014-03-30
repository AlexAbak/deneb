/**
 * 
 */
package org.deneblingvo.transformator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author alex
 *
 */
public class PlainXmlSerializer {

	private OutputStream output;

	public PlainXmlSerializer(OutputStream output) {
		this.output = output;
	}

	public void serialize(Document document) throws IOException, NotImplementNodeType {
		String encoding = document.getXmlEncoding();
		if (encoding == null) {
			encoding = "UTF-8";	
		}
		Writer writer = new OutputStreamWriter(this.output, encoding);
		writer.write("<?xml version=\"");
		writer.write(document.getXmlVersion());
		writer.write("\" encoding=\"");
		writer.write(encoding);
		writer.write("\" ?>\n");
		serializeNodes(writer, document.getChildNodes(), 0);
		writer.close();
	}

	private void serializeNodes(Writer writer, NodeList nodes, int deep) throws NotImplementNodeType, IOException {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			serializeNode(writer, node, deep);
		}
	}

	private void serializeNode(Writer writer, Node node, int deep) throws NotImplementNodeType, IOException {
		switch (node.getNodeType()) {
		case Node.ATTRIBUTE_NODE:
			throw new NotImplementNodeType();
		case Node.CDATA_SECTION_NODE:
			throw new NotImplementNodeType();
		case Node.COMMENT_NODE:
			throw new NotImplementNodeType();
		case Node.DOCUMENT_NODE:
			throw new NotImplementNodeType();
		case Node.ELEMENT_NODE:
			serializeElement(writer, node, deep);
			break;
		case Node.TEXT_NODE:
			throw new NotImplementNodeType();
		default: {
				throw new NotImplementNodeType();
			}
		}
	}

	private void serializeElement(Writer writer, Node node, int deep) throws NotImplementNodeType, IOException {
		serializeDeep(writer, deep);
		writer.write("<");
		writer.write(node.getNodeName());
		writer.write(" />");
	}

	private void serializeDeep(Writer writer, int deep) throws IOException {
		for (int i = 0; i < deep; i++) {
			writer.write("\t");
		}
	}
	
}
