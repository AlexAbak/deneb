import com.beust.jcommander.*;
import java.io.*;

public class DenebCompilerParameters {

	@Parameter(
		names ={ "-f", "--file"},
		description = "имя файла",
		converter = FileConverter.class,
		required = true
	)
	File file;

	@Parameter(
		names ={ "-n", "--notation"},
		description = "нотация",
		converter = FileConverter.class
	)
	File notation;

	@Parameter(
		names ={ "-x", "--xml"},
		description = "xml нотация",
		converter = FileConverter.class
	)
	File xml;

	@Parameter(
		names = { "-h", "--help" },
		description = "справка",
		help = true
	)
	boolean help = false;

}
