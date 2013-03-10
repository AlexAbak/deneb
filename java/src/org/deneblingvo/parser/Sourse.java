package org.deneblingvo.parser;

import java.io.InputStream;

import org.w3c.dom.Document;

public class Sourse implements Sourseable {

	private Document document;

	@Override
	public Document getDocument() {
		return this.document;
	}

	public Sourse (Document document) {
		this.document = document;
	}

	@Override
	public InputStream getStream() {
		// TODO Auto-generated method stub
		return null;
	}

}
