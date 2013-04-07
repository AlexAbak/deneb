package org.deneblingvo.parser;

import org.deneblingvo.deneb.ParserResultable;
import org.deneblingvo.notation.Notationable;


public interface Parserable {

	public ParserResultable parse(Notationable notation, Sourseable source) throws ENotSupportedNotationable;

}
