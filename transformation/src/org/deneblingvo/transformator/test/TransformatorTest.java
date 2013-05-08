/**
 * 
 */
package org.deneblingvo.transformator.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import net.sf.saxon.s9api.SaxonApiException;

import org.deneblingvo.transformator.Transformator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * @author itprog10
 *
 */
public class TransformatorTest {

	private Transformator transformator;
	private InputStream transformationStream;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.transformator = new Transformator();
		this.transformationStream = new FileInputStream("xml/test/Example.xml");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.deneblingvo.transformator.Transformator#transformate(java.io.InputStream)}.
	 * @throws TransformerException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws XPathExpressionException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws SaxonApiException 
	 */
	@Test
	public void testTransformate() throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, XPathExpressionException, ParserConfigurationException, SAXException, IOException, TransformerException, SaxonApiException {
		this.transformator.transformate(this.transformationStream);
	}

}
