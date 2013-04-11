package org.deneblingvo.serialization.xml;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.deneblingvo.serialization.xml.Xpath;

public class Reader {

	private void readNode(Element element, Object object) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, XPathExpressionException {
		Class<?> objectClass = object.getClass();
		Xpath annotation = objectClass.getAnnotation(Xpath.class);
		if (annotation != null) {
			System.out.println(annotation.path());
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expr = xpath.compile(annotation.path());
			Object result = expr.evaluate(element, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			for (int i = 0; i < nodes.getLength(); i++) {
			    System.out.println("[" + nodes.item(i).getNodeValue() + "]"); 
			}
		}
	}

	public void read (Document document, Object object) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, XPathExpressionException {
		this.readNode(document.getDocumentElement(), object);
	}

}
