package org.deneblingvo.parser;

import org.deneblingvo.deneb.ParserResultable;
import org.deneblingvo.notation.Notationable;
import org.deneblingvo.notation.NotationStringable;
import org.junit.Assert;
import org.w3c.dom.Node;

public class ParserString implements Parserable {

	private ParserResultable parseString(NotationStringable string, Sourseable source) {
		Node node = source.getDocument().createElementNS("", "string");
		Assert.assertTrue("string", false);
		return new ParserResult(node);
	}

	@Override
	public ParserResultable parse(Notationable notation, Sourseable source) throws ENotSupportedNotationable {
		if (notation instanceof NotationStringable) {
			NotationStringable string = (NotationStringable) notation;
			return parseString(string, source);
		} else {
			throw new ENotSupportedNotationable("Support only Stringable");
		}
	}

}
