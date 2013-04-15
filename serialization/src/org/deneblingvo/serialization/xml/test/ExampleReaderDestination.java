package org.deneblingvo.serialization.xml.test;

import java.util.Vector;

import org.deneblingvo.serialization.xml.Xpath;

@Xpath(path = "/root")
public class ExampleReaderDestination {

	@Xpath(path = "@int_attribute", value = true)
	public int int_attribute;
	
	@Xpath(path = "@string_attribute", value = true)
	public String string_attribute;

	@Xpath(path = "field_attribute")
	public ExampleReaderDestinationField field_attribute;

	@Xpath(path = "field_attributes", itemClass = ExampleReaderDestinationField.class)
	public Vector<ExampleReaderDestinationField> field_attributes;
}
