package org.deneblingvo.notation;


public class NotationString implements NotationStringable {

	private java.lang.String value;

	@Override
	public java.lang.String getValue() {
		return this.value;
	}

	public NotationString(java.lang.String value) {
		this.value = value;
	}
}