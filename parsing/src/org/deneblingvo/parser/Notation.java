package org.deneblingvo.parser;

/**
 * @author Алексей Кляузер <drum@pisem.net>
 * Нотация содержит список элементов нотации
 */
import java.util.Vector;

import org.deneblingvo.serialization.xml.Xpath;

@Xpath(path = "//den:notation", namespaces = {"den", "http://deneblingvo.org/xsd/notation/1.0"})
public class Notation {

	@Xpath(path = "den:definition", itemClass = Definition.class, namespaces = {"den", "http://deneblingvo.org/xsd/notation/1.0"})
	public Vector<Definition> definitions;

}
