/**
 * 
 */
package org.deneblingvo.serialization.xml.test;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Assert;

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
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream source = new FileInputStream("xml/Example.xml");
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
		
		Assert.assertEquals(1234321, this.example.int_attribute);
		Assert.assertEquals("qwerewq", this.example.string_attribute);
		Assert.assertEquals(12321, this.example.field_attribute.int_attribute);
		Assert.assertEquals("qwewq", this.example.field_attribute.string_attribute);
		Assert.assertEquals(2, this.example.field_attributes.size());
		Assert.assertEquals(121, this.example.field_attributes.get(0).int_attribute);
		Assert.assertEquals("qwq", this.example.field_attributes.get(0).string_attribute);
		Assert.assertEquals(1, this.example.field_attributes.get(1).int_attribute);
		Assert.assertEquals("q", this.example.field_attributes.get(1).string_attribute);
	}

}
