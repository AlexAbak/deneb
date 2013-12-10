package org.deneblingvo.serialization.xml;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Vector;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Reader {
	
	private XPathFactory factory;
	
	public Reader(XPathFactory factory) {
		this.factory = factory;
	}
	
	private void readFieldValue(Node node, Object obj, Field field, Class<?> fieldClass) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		String s = node.getNodeValue();
		if (fieldClass.isPrimitive()) {
			if (fieldClass == Boolean.TYPE) {
				boolean v = Boolean.parseBoolean(s);
				field.setBoolean(obj, v);
			} else if (fieldClass == Character.TYPE) {
				char v = s.charAt(0);
				field.setChar(obj, v);
			} else if (fieldClass == Byte.TYPE) {
				byte v = Byte.parseByte(s);
				field.setByte(obj, v);
			} else if (fieldClass == Short.TYPE) {
				short v = Short.parseShort(s);
				field.setShort(obj, v);
			} else if (fieldClass == Integer.TYPE) {
				int v = Integer.parseInt(s);
				field.setInt(obj, v);
			} else if (fieldClass == Long.TYPE) {
				long v = Long.parseLong(s);
				field.setLong(obj, v);
			} else if (fieldClass == Float.TYPE) {
				float v = Float.parseFloat(s);
				field.setFloat(obj, v);
			} else if (fieldClass == Double.TYPE) {
				double v = Double.parseDouble(s);
				field.setDouble(obj, v);
			} else if (fieldClass == Void.TYPE) {
				
			}
		} else {
			if (fieldClass == String.class) {
				field.set(obj, s);
			} else {
				throw new IllegalArgumentException("Not supported class: " + fieldClass.getCanonicalName());
			}
		}
	}

	private void readFieldObject(Node node, Object obj, Field field, Class<?> fieldClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, XPathExpressionException {
		Object v = fieldClass.newInstance();
		field.set(obj, v);
		this.readElementNode(node, v, fieldClass);
	}
	
	private void readFieldObjects(NodeList nodes, Object obj, Field field, Class<?> fieldClass, Xpath annotation) throws InstantiationException, IllegalAccessException, IllegalArgumentException, XPathExpressionException {
		if (fieldClass == Vector.class) {
			Object v = fieldClass.newInstance();
			field.set(obj, v);
			@SuppressWarnings("unchecked")
			Vector<Object> vector = (Vector<Object>)v;
			Class<?> itemClass = annotation.itemClass();
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				Object n = itemClass.newInstance();
				this.readElementNode(node, n, itemClass);
				vector.addElement(n);
			}
		} else {
			Node node = nodes.item(0);
			this.readFieldObject(node, obj, field, fieldClass);
		}
	}

	private void readFieldXpath(Node root, Object obj, Field field, Class<?> fieldClass, Xpath annotation) throws XPathExpressionException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		XPath xpath = this.factory.newXPath();
		if (annotation.namespaces().length > 0) {
			NamespaceContext nsContext = new NamespaceContextMap(annotation.namespaces());
			xpath.setNamespaceContext(nsContext);
		}
		XPathExpression expr = xpath.compile(annotation.path());
		Object result = expr.evaluate(root, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		if (annotation.value()) {
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				this.readFieldValue(node, obj, field, fieldClass);
			}
		} else {
			this.readFieldObjects(nodes, obj, field, fieldClass, annotation);
		}
	}

	private void readElementNode(Node root, Object object, Class<?> objectClass) throws IllegalArgumentException, IllegalAccessException, XPathExpressionException, InstantiationException {
		Field fields[] = objectClass.getFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Xpath annotation = field.getAnnotation(Xpath.class);
			if (annotation != null) {
				Class<?> fieldClass = field.getType();
				this.readFieldXpath(root, object, field, fieldClass, annotation);
			}
		}
	}

	private void readNode(Element element, Object object) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, XPathExpressionException {
		Class<?> objectClass = object.getClass();
		Xpath annotation = objectClass.getAnnotation(Xpath.class);
		if (annotation != null) {
			XPath xpath = this.factory.newXPath();
			if (annotation.namespaces().length > 0) {
				NamespaceContext nsContext = new NamespaceContextMap(annotation.namespaces());
				xpath.setNamespaceContext(nsContext);
			}
			XPathExpression expr = xpath.compile(annotation.path());
			Object result = expr.evaluate(element, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				this.readElementNode(node, object, objectClass);
			}
		}
	}

	public void read (Document document, Object object) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, XPathExpressionException {
		this.readNode(document.getDocumentElement(), object);
	}

	public void read (InputStream stream, Object object) throws ParserConfigurationException, SAXException, IOException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, XPathExpressionException {
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(stream);
		this.read(document, object);
	}
}
