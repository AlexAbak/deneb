package ru.myweek_end.deneb.parser;

import ru.myweek_end.deneb.ParserResultable;
import ru.myweek_end.deneb.notation.Notationable;

public interface Parserable {

	public ParserResultable parse(Notationable notation, Sourseable source);

}
