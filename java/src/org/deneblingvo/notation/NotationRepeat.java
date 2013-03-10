package org.deneblingvo.notation;

public class NotationRepeat implements NotationRepeatable {
	
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
	
	public NotationRepeat (Integer min, Integer max, Notationable body) {
		this.min = min;
		this.max = max;
		this.body = body;
	}

}
