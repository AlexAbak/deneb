package org.deneblingvo.parser;

import org.deneblingvo.serialization.xml.Xpath;

/**
 * @author Алексей Кляузер <drum@pisem.net>
 * Определение повтора
 */
public class DefinitionRepeat {

	@Xpath(path = "den:ranges", namespaces = {"den", "http://deneblingvo.org/xsd/notation/1.0"})
	public RepeatRanges ranges;
	
	@Xpath(path = "den:body", namespaces = {"den", "http://deneblingvo.org/xsd/notation/1.0"})
	public RepeatBody body;
	
	@Xpath(path = "den:glue", namespaces = {"den", "http://deneblingvo.org/xsd/notation/1.0"})
	public RepeatGlue glue;
	
}
