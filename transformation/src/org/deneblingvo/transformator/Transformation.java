package org.deneblingvo.transformator;

import java.util.Vector;

import org.deneblingvo.serialization.xml.Xpath;

@Xpath(path = "//dtn:transformation", namespaces = {"dtn", "http://deneblingvo.org/xsd/transformation/1.0"})
public class Transformation {

	@Xpath(path = "dtn:stylesheet", namespaces = {"dtn", "http://deneblingvo.org/xsd/transformation/1.0"})
	public Stylesheet stylesheet;

	@Xpath(path = "dtn:source", itemClass = Source.class, namespaces = {"dtn", "http://deneblingvo.org/xsd/transformation/1.0"})
	public Vector<Source> source;

}
