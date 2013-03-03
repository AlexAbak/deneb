package ru.myweek_end.deneb.language;

import ru.myweek_end.deneb.notation.*;

public class Notation extends Repeat {
	
	private static final Notationable notationItem = new NotationItem();

	public Notation() {
		super (0, 0, notationItem);
	}

}