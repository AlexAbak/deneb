package org.deneblingvo.parser;

import java.util.*;

public class Parsers {
	
	private Map<Class<?>, Parserable> records = new HashMap<Class<?>, Parserable>();

	public Map<Class<?>, Parserable> getRecords() {
		return this.records;
	}
	
	public void AddParser(Class<?> notation, Parserable parser) {
		this.records.put(notation, parser);
	}

}
