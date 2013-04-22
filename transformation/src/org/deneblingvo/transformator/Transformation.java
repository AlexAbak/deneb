package org.deneblingvo.transformator;

import java.util.Vector;

import org.deneblingvo.serialization.xml.Xpath;

@Xpath(path = "/transformation")
public class Transformation {

	@Xpath(path = "stylesheet")
	public Stylesheet stylesheet;

	@Xpath(path = "source", itemClass = Source.class)
	public Vector<Source> source;

}
