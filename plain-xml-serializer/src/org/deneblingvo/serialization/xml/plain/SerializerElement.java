package org.deneblingvo.serialization.xml.plain;
import java.io.*;

import org.w3c.dom.*;

public class SerializerElement implements SerializerItem {

	public boolean canSerialize (Node node) {
		return node.getNodeType() == Node.ELEMENT_NODE;
	}

	public void serialize (Serializer serializer, Writer writer, NsList list, Node node, int deep) {
		Element element = (Element) node;
		try {
			serializeDeep(writer, deep);
			String fullName = this.getFullName(list, element);
			writer.write("<");
			writer.write(fullName);
			int indent = fullName.length() + 2;
			boolean first = true;
			if (deep == 0) {
				for (String namespaceURI : list.getNamespaces().keySet()) {
					String attrName;
					String prefix = list.getPrefix(namespaceURI);
					if (prefix == null) {
						attrName = "xmlns";
					} else {
						attrName = "xmlns:" + prefix;
					}
					first = serializeAttr(writer, attrName, namespaceURI, deep, indent, first);
				}
			}
			NamedNodeMap attributes = element.getAttributes();
			for (int i = 0; i < attributes.getLength(); i++) {
				Attr attr = (Attr) attributes.item(i);
				if (attr.getNamespaceURI() != "http://www.w3.org/2000/xmlns/") {
					String attrName = this.getFullName(list, attr);
					first = serializeAttr(writer, attrName, attr.getValue(), deep, indent, first);
				}
			}
			NodeList childs = element.getChildNodes();
			if ((childs != null) && (childs.getLength() != 0)) {
				writer.write(">\n");
				serializer.serializeNodes(writer, list, childs, deep + 1);
				serializeDeep(writer, deep);
				writer.write("</");
				writer.write(fullName);
				writer.write(">\n");
			} else {
				writer.write("/>\n");
			}
		} catch (NotImplementNodeType e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean serializeAttr(Writer writer, String name, String value, int deep, int indent, boolean first) throws IOException {
		if (first) {
			writer.write(" ");
			writer.write(name);
			writer.write("=\"");
			writer.write(value);
			writer.write("\"");
		} else {
			writer.write("\n");
			this.serializeDeep(writer, deep);
			this.serializeIndent(writer, indent);
			writer.write(name);
			writer.write("=\"");
			writer.write(value);
			writer.write("\"");
		}
		return false;
	}

	private String getFullName(NsList list, Node node) {
		String ret; 
		String prefix = list.getPrefix(node.getNamespaceURI());
		if (prefix != null) {
			ret = prefix + ":" + node.getLocalName();
		} else {
			ret = node.getLocalName();
		}
		return ret;
	}

	private void serializeDeep(Writer writer, int deep) throws IOException {
		for (int i = 0; i < deep; i++) {
			writer.write("\t");
		}
	}

	private void serializeIndent(Writer writer, int indent) throws IOException {
		for (int i = 0; i < indent; i++) {
			writer.write(" ");
		}
	}

}
