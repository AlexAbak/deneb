package ru.myweek_end.deneb.parser;

import ru.myweek_end.deneb.notation.*;

import java.io.*;
import java.util.Map.*;

public class Parser {

	private ParserList parserList = new ParserList(); 

	public void RegisterParser(Class<Notationable> notation, Parserable parser) {
		parserList.AddParser(notation, parser);
	}

	private void ParseItem(Notationable notation, Sourseable source) {
		for (Entry<Class<Notationable>, Parserable> entry: parserList.getRecords().entrySet()) {
			Class<Notationable> notationClass = entry.getKey();
			if (notationClass.isInstance(notation)) {
				Parserable parser = entry.getValue();
				parser.parse(notation, source);
			}
		}		    
	}

	Notationable notation;

	public Parser(Notationable notation) {
		this.notation = notation;
	}

	public void Parse(InputStream source) {
		Sourseable parserSourse = new Sourse();
		ParseItem(this.notation, parserSourse);
	}
}
