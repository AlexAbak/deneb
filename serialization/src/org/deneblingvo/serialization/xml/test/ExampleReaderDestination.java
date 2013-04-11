package org.deneblingvo.serialization.xml.test;

import java.util.Vector;

import org.deneblingvo.serialization.xml.Xpath;

@Xpath(path = "//root")
public class ExampleReaderDestination {

	public Integer int_attribute;
	
	public String string_attribute;
	
	public ExampleReaderDestinationField field_attribute;

	public Vector<ExampleReaderDestinationField> field_attributes;
}
