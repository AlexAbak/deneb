package ru.myweek_end.deneb.notation;

public interface Repeatable extends Notationable  {

	Integer getMin();

	Integer getMax();

	Notationable getBody();

}