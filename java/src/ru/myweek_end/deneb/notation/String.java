package ru.myweek_end.deneb.notation;


public class String implements Stringable {

	private java.lang.String body;

	@Override
	public java.lang.String getBody() {
		return this.body;
	}

	public String(java.lang.String body) {
		this.body = body;
	}
}