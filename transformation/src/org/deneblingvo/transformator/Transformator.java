package org.deneblingvo.transformator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathExpressionException;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmDestination;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;

import org.deneblingvo.serialization.xml.Reader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Transformator {

	/**
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws XPathExpressionException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws TransformerException 
	 * @throws SaxonApiException 
	 */
	public void transformate(InputStream transformationStream) throws ParserConfigurationException, SAXException, IOException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, XPathExpressionException, TransformerException, SaxonApiException {

		Transformation transformation = new Transformation();
		Reader reader = new Reader();
		reader.read(transformationStream, transformation);

		Processor processor = new Processor(false);
		StreamSource stylesheetSource = new StreamSource(transformation.stylesheet.href);
		XsltCompiler xsltCompiler = processor.newXsltCompiler();
		XsltExecutable xsltExecutable = xsltCompiler.compile(stylesheetSource);
		XsltTransformer xsltTransformer = xsltExecutable.load();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		Node root = document.createElementNS("http://deneblingvo.org/xsd/transformator/list/1.0", "lst:list");
		document.appendChild(root);
		
		for (int i = 0; i < transformation.source.size(); i++) {
			File tsFile = new File(transformation.source.get(i).href);
			Document tsDocument = documentBuilder.parse(tsFile);
			Node tsNode = document.adoptNode(tsDocument.getDocumentElement());
			root.appendChild(tsNode);
		}
		DOMSource source = new DOMSource(document);
		xsltTransformer.setSource(source);

		XdmDestination destination = new XdmDestination();
		xsltTransformer.setDestination(destination);
		xsltTransformer.transform();
		XdmNode xdmNode = destination.getXdmNode();
		File file = new File(transformation.destination.href);
		Serializer serializer = processor.newSerializer(file);
		serializer.serializeNode(xdmNode);
	}

}
