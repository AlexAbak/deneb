package org.deneblingvo.language;

import org.deneblingvo.notation.*;

public class Notation extends NotationRepeat {
	
	private static final Notationable notationItem = new NotationItem();

	public Notation() {
		super (0, 0, notationItem);
	}

}