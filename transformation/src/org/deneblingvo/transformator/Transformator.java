package org.deneblingvo.transformator;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.xpath.*;
import net.sf.saxon.s9api.*;
import net.sf.saxon.xpath.*;
import org.deneblingvo.serialization.xml.plain.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import org.deneblingvo.serialization.xml.Reader;
import javax.xml.parsers.DocumentBuilder;
import org.deneblingvo.serialization.xml.plain.Serializer;

public class Transformator {

	/**
	 * @param source
	 * @return
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws SaxonApiException 
	 * @throws NotImplementNodeType 
	 */
	private DOMSource createSource(boolean isDebug, Vector<Source> source) throws ParserConfigurationException, SAXException, IOException, SaxonApiException, NotImplementNodeType {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		Node root = document.createElementNS("http://deneblingvo.org/xsd/transformator/list/1.0", "lst:list");
		document.appendChild(root);
		for (int i = 0; i < source.size(); i++) {
			File tsFile = new File(source.get(i).href);
			Document tsDocument = documentBuilder.parse(tsFile);
			Node tsNode = document.adoptNode(tsDocument.getDocumentElement());
			root.appendChild(tsNode);
		}
		if (isDebug) {
			saveSource(document, "source.xml");
		}
		return new DOMSource(document);
	}
	
	/**
	 * @param document
	 * @param href
	 * @return
	 * @throws SaxonApiException 
	 * @throws NotImplementNodeType 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private void saveSource(Document document, String href) throws SaxonApiException, FileNotFoundException, IOException, NotImplementNodeType {
		Serializer serializer = new Serializer();
		serializer.serialize(document, new FileOutputStream(new File(href)));
	}

	/**
	 * @param destination
	 * @return
	 * @throws ParserConfigurationException 
	 */
	private Document createDestination() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		return documentBuilder.newDocument();
	}

	/**
	 * @param destination
	 * @param dest
	 * @throws ParserConfigurationException 
	 * @throws SaxonApiException 
	 * @throws IOException 
	 * @throws NotImplementNodeType 
	 */
	private void saveDestinations(boolean isDebug, Processor processor, Document destination, Destination dest) throws ParserConfigurationException, SaxonApiException, IOException, NotImplementNodeType {
		if (isDebug) {
			Serializer serializer = new Serializer();
			serializer.serialize(destination, new FileOutputStream(new File("debug.xml")));
		}

		Element element = destination.getDocumentElement();
		NodeList nodes = element.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element cur = (Element)node;
				String href = "";
				if (cur.hasAttribute("href")) {
					href = cur.getAttribute("href");
				} else {
					href = dest.href;
				}
				boolean text = false;
				if (cur.hasAttribute("text")) {
					text = cur.getAttribute("text").equals("true");
				} else {
					text = dest.text;
				}
				if (text) {
					this.saveTextDestination(processor, cur, href);
				} else {
					this.saveDestination(processor, cur, href);
				}
			}
		}
	}

	/**
	 * @param cur
	 * @param href
	 * @throws ParserConfigurationException 
	 * @throws SaxonApiException 
	 */
	private void saveTextDestination(Processor processor, Element element, String href) {
		String value = "";
		NodeList nodes = element.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.TEXT_NODE) {
				Text cur = (Text)node;
				value = value.concat(cur.getNodeValue());
			}
		}
		File file = new File(href);
		FileWriter fileWriter;
		try {
			File dir = file.getCanonicalFile().getParentFile();
			dir.mkdirs();
			fileWriter = new FileWriter(file);
			fileWriter.write(value);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param cur
	 * @param href
	 * @throws ParserConfigurationException 
	 * @throws SaxonApiException 
	 * @throws IOException 
	 * @throws NotImplementNodeType 
	 */
	private void saveDestination(Processor processor, Element cur, String href) throws ParserConfigurationException, SaxonApiException, IOException, NotImplementNodeType {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		Element sourceRoot = this.getSourceRoot(cur);
		Node root = document.adoptNode(sourceRoot);
		document.appendChild(root);

		File file = new File(href);
		try {
			File dir = file.getCanonicalFile().getParentFile();
				dir.mkdirs();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Serializer serializer = new Serializer();
		serializer.serialize(document, new FileOutputStream(file));
	}

	/**
	 * @param element
	 * @return
	 */
	private Element getSourceRoot(Element element) {
		NodeList nodes = element.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				return (Element)node;
			}
		}
		return null;
	}

	/**
	 * @param transformationStream
	 * @return
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws XPathExpressionException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws SaxonApiException 
	 * @throws NotImplementNodeType 
	 */
	public void transformate(boolean isDebug, InputStream transformationStream) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, XPathExpressionException, ParserConfigurationException, SAXException, IOException, SaxonApiException, NotImplementNodeType {

		Transformation transformation = new Transformation();
		Reader reader = new Reader(new XPathFactoryImpl());
		reader.read(transformationStream, transformation);

		Processor processor = new Processor(false);
		StreamSource stylesheetSource = new StreamSource(transformation.stylesheet.href);
		XsltCompiler xsltCompiler = processor.newXsltCompiler();
		XsltExecutable xsltExecutable = xsltCompiler.compile(stylesheetSource);
		XsltTransformer xsltTransformer = xsltExecutable.load();

		xsltTransformer.setSource(this.createSource(isDebug, transformation.source));
		Document destination = this.createDestination();
		xsltTransformer.setDestination(new DOMDestination(destination));
		xsltTransformer.transform();
		
		this.saveDestinations(isDebug, processor, destination, transformation.destination);
	}

}
