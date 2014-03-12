package org.deneblingvo.parser;

import org.deneblingvo.serialization.xml.Xpath;

/**
 * @author Алексей Кляузер <drum@pisem.net>
 * Определение строки
 */
public class DefinitionString {

	@Xpath(path = "@value", value = true)
	public String value;
	
}
