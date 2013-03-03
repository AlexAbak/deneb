package ru.myweek_end.deneb.notation;

public class Repeat implements Repeatable {
	
	Integer min;
	Integer max;
	Notationable body;

	@Override
	public Integer getMin() {
		return this.min;
	}

	@Override
	public Integer getMax() {
		return this.max;
	}

	@Override
	public Notationable getBody() {
		return this.body;
	}
	
	public Repeat (Integer min, Integer max, Notationable body) {
		this.min = min;
		this.max = max;
		this.body = body;
	}

}
