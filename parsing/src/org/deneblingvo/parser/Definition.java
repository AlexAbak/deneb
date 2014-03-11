package org.deneblingvo.parser;

import org.deneblingvo.serialization.xml.Xpath;

/**
 * @author Алексей Кляузер <drum@pisem.net>
 * Определение лексемы
 */
public class Definition {

	@Xpath(path = "@lexeme", value = true)
	public String lexeme;

	@Xpath(path = "den:string", namespaces = {"den", "http://deneblingvo.org/xsd/notation/1.0"})
	public DefinitionString definitionString;

	@Xpath(path = "den:characters", namespaces = {"den", "http://deneblingvo.org/xsd/notation/1.0"})
	public DefinitionCharacters definitionCharacters;
	
	@Xpath(path = "den:choice", namespaces = {"den", "http://deneblingvo.org/xsd/notation/1.0"})
	public DefinitionChoice definitionChoice;
	
	@Xpath(path = "den:repeat", namespaces = {"den", "http://deneblingvo.org/xsd/notation/1.0"})
	public DefinitionRepeat definitionRepeat;
	
	@Xpath(path = "den:list", namespaces = {"den", "http://deneblingvo.org/xsd/notation/1.0"})
	public DefinitionList definitionList;
	
	@Xpath(path = "den:lexeme", namespaces = {"den", "http://deneblingvo.org/xsd/notation/1.0"})
	public DefinitionLexeme definitionLexeme;

}
