package org.deneblingvo.transformator;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathExpressionException;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.SAXDestination;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;

import org.deneblingvo.serialization.xml.Reader;
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
		
		for (int i = 0; i < transformation.source.size(); i++) {
			StreamSource transformationSource = new StreamSource(transformation.source.get(i).href);
			xsltTransformer.setSource(transformationSource);
		}
		SAXDestination destination = new SAXDestination(null);
		xsltTransformer.setDestination(destination);
		xsltTransformer.transform();
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
