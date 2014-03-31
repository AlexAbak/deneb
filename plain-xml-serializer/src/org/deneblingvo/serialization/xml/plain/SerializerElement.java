package org.deneblingvo.serialization.xml.plain;
import java.io.*;
import org.w3c.dom.*;

public class SerializerElement implements SerializerItem {

	public boolean canSerialize (Node node) {
		return node.getNodeType() == Node.ELEMENT_NODE;
	}

	public void serialize (Writer writer, NsList list, Node node, int deep) {
		Element element = (Element) node;
		try {
			serializeDeep(writer, deep);
			writer.write("<");
			String prefix = list.getPrefix(element.getNamespaceURI());
			if (prefix != null) {
				writer.write(prefix);
				writer.write(":");
			}
			writer.write(element.getLocalName());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void serializeDeep(Writer writer, int deep) throws IOException {
		for (int i = 0; i < deep; i++) {
			writer.write("\t");
		}
	}

}
