package ru.myweek_end.deneb.notation;

import ru.myweek_end.deneb.parser.Parserable;
import java.util.*;

public class ParserList {
	
	private Map<Class<Notationable>, Parserable> records = new HashMap<Class<Notationable>, Parserable>();

	public Map<Class<Notationable>, Parserable> getRecords() {
		return this.records;
	}
	
	public void AddParser(Class<Notationable> notation, Parserable parser) {
		this.records.put(notation, parser);
	}

}
