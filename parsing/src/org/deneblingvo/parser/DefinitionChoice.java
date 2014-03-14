package org.deneblingvo.parser;

import java.util.Vector;

import org.deneblingvo.serialization.xml.Xpath;

/**
 * @author Алексей Кляузер <drum@pisem.net>
 * Определение выбора одного из вариантов
 */
public class DefinitionChoice {

	@Xpath(path = "den:lexeme", itemClass = DefinitionLexeme.class, namespaces = {"den", "http://deneblingvo.org/xsd/notation/1.0"})
	public Vector<DefinitionLexeme> lexemes;

}
