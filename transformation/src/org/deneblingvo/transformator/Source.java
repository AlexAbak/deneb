package org.deneblingvo.transformator;

import org.deneblingvo.serialization.xml.Xpath;

public class Source {

	@Xpath(path = "@href", value = true)
	public String href;

}
