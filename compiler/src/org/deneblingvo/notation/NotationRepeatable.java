package org.deneblingvo.notation;

public interface NotationRepeatable extends Notationable  {

	Integer getMin();

	Integer getMax();

	Notationable getBody();

}