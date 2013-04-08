package org.deneblingvo.transformator;

import com.beust.jcommander.Parameter;
import java.io.File;

public class Parameters {
	
	@Parameter(
		names ={ "-f", "--file"},
		description = "имя файла",
		converter = FileConverter.class
	)
	public 	File file;

	@Parameter(
		names = { "-h", "--help" },
		description = "справка",
		help = true
	)
	boolean help = false;

}
