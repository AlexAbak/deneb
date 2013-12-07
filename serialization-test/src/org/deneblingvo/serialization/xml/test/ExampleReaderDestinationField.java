package org.deneblingvo.serialization.xml.test;

import org.deneblingvo.serialization.xml.Xpath;

public class ExampleReaderDestinationField {

	@Xpath(path = "@int_attribute", value = true)
	public int int_attribute;

	@Xpath(path = "@string_attribute", value = true)
	public String string_attribute;

}
