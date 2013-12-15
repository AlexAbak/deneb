package org.deneblingvo.transformator;

import org.deneblingvo.serialization.xml.Xpath;

public class Destination {

	@Xpath(path = "@href", value = true)
	public String href;

	@Xpath(path = "@text", value = true)
	public boolean text;

}
