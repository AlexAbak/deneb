package org.deneblingvo.parser;

import org.deneblingvo.serialization.xml.Xpath;

/**
 * @author Алексей Кляузер <drum@pisem.net>
 * Определение диапазона символов
 */
public class DefinitionCharacters {

	@Xpath(path = "@min", value = true)
	public String min;
	
	@Xpath(path = "@max", value = true)
	public String max;

}
