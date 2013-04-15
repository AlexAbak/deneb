package org.deneblingvo.serialization.xml;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE, ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Xpath {

	String path();

	boolean value() default false;
	
	Class<?> itemClass() default Object.class;

}
