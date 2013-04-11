/**
 * 
 */
package org.deneblingvo.serialization.xml.test;

// import static org.junit.Assert.*;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

// import junit.framework.Assert;

import org.deneblingvo.serialization.xml.Reader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 * @author alex
 *
 */
public class ReaderTest {
	
	private ExampleReaderDestination example;
	private Reader reader;
	private Document document;

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
		this.example = new ExampleReaderDestination();
		this.reader = new Reader();
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream source = new FileInputStream("Example.xml");
		this.document = builder.parse(source);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.deneblingvo.serialization.xml.Reader#read(org.w3c.dom.Document, java.lang.Object)}.
	 */
	@Test
	public void testRead() throws Exception {
		this.reader.read(this.document, this.example);
	}

}
