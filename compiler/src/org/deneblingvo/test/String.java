package org.deneblingvo.test;

//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.Reader;
//import java.io.StringReader;
//import java.nio.charset.Charset;

import javax.xml.parsers.ParserConfigurationException;

import org.deneblingvo.notation.NotationString;
import org.deneblingvo.notation.NotationStringable;
import org.deneblingvo.notation.Notationable;
import org.deneblingvo.parser.ENotSupportedNotationable;
import org.deneblingvo.parser.Parser;
import org.deneblingvo.parser.ParserString;
//import org.deneblingvo.parser.Sourse;
//import org.deneblingvo.parser.Sourseable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

public class String {
	
	private Notationable notation;
	private Parser parser;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.notation = (Notationable) new NotationString("test");
		this.parser = new Parser(this.notation);
		this.parser.RegisterParser(NotationStringable.class, new ParserString());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws ENotSupportedNotationable, ParserConfigurationException, SAXException, IOException {
//		ByteArrayOutputStream output = new ByteArrayOutputStream();
//		OutputStreamWriter writer = new OutputStreamWriter(output, "test.");
//		InputStream input = new ByteArrayInputStream(output.toByteArray());
//		Sourseable source = new Sourse(input);
//		this.parser.Parse(source);
	}

}
