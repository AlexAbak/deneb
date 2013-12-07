package org.deneblingvo.serialization.xml.test;

import java.util.Vector;

import org.deneblingvo.serialization.xml.Xpath;

@Xpath(path = "//exm:root", namespaces = {"exm", "http://deneblingvo.org/xsd/Example/1.0"}) // 
//@Xpath(path = "/root") // Jaxp
public class ExampleReaderDestination {

	@Xpath(path = "@int_attribute", value = true)
	public int int_attribute;
	
	@Xpath(path = "@string_attribute", value = true)
	public String string_attribute;

	@Xpath(path = "exm:field_attribute", namespaces = {"exm", "http://deneblingvo.org/xsd/Example/1.0"}) // Saxon
	//@Xpath(path = "field_attribute") // Jaxp
	public ExampleReaderDestinationField field_attribute;

	@Xpath(path = "exm:field_attributes", itemClass = ExampleReaderDestinationField.class, namespaces = {"exm", "http://deneblingvo.org/xsd/Example/1.0"}) // Saxon
	//@Xpath(path = "field_attributes", itemClass = ExampleReaderDestinationField.class) // Jaxp
	public Vector<ExampleReaderDestinationField> field_attributes;
}
