package org.deneblingvo.parser;

import java.io.InputStream;

import org.w3c.dom.Document;

public interface Sourseable {

	public Document getDocument();

	public InputStream getStream();

}
