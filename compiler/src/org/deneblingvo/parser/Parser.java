package org.deneblingvo.parser;


import java.io.*;
import java.util.Map.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.deneblingvo.notation.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Parser {

	private Parsers parserList = new Parsers(); 

	public void RegisterParser(Class<?> notation, Parserable parser) {
		parserList.AddParser(notation, parser);
	}

	private void ParseItem(Notationable notation, Sourseable source) throws ENotSupportedNotationable {
		for (Entry<Class<?>, Parserable> entry: parserList.getRecords().entrySet()) {
			Class<?> notationClass = entry.getKey();
			if (notationClass.isInstance(notation)) {
				Parserable parser = entry.getValue();
				parser.parse(notation, source);
			}
		}		    
	}

	Notationable notation;

	public Parser(Notationable notation) {
		this.notation = notation;
	}

	public void Parse(InputStream source) throws ENotSupportedNotationable, ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder loader = factory.newDocumentBuilder();
	    Document document = loader.newDocument();
		Sourseable parserSourse = new Sourse(document);
		ParseItem(this.notation, parserSourse);
	}
}
