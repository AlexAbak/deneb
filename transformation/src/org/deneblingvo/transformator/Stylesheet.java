package org.deneblingvo.transformator;

import org.deneblingvo.serialization.xml.Xpath;

public class Stylesheet {

	@Xpath(path = "@href", value = true)
	public String href;

}
