package org.deneblingvo.transformator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.XPathExpressionException;

import net.sf.saxon.TransformerFactoryImpl;

import org.deneblingvo.serialization.xml.Reader;
import org.w3c.dom.Document;
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
	 */
	public void transformate(InputStream transformationStream) throws ParserConfigurationException, SAXException, IOException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, XPathExpressionException, TransformerException {
	
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(transformationStream);

		Transformation transformation = new Transformation();
		Reader reader = new Reader();
		reader.read(document, transformation);

		if (transformation.stylesheet == null) {
			System.out.println("transformation.stylesheet == null");
		}
		Document stylesheet = builder.parse(transformation.stylesheet.href);
		/*
		Vector<Document> sources = new Vector<Document>();

		for (Source source : transformation.source) {
			Document sourceDocument = builder.parse(source.href);
			sources.add(sourceDocument);
		};

		DOMSource stylesheetSource = new DOMSource(stylesheet);

		TransformerFactory tfactory = TransformerFactoryImpl.newInstance();
		Transformer transformer = tfactory.newTransformer(stylesheetSource);
		for (Document source : sources) {
			DOMSource sourceSource = new DOMSource(source);
			Document outputDocument = builder.newDocument();
			DOMResult output = new DOMResult(outputDocument);
			transformer.transform(sourceSource, output);
		}
*/
	}

}
