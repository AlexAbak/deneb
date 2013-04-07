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
		names = { "-h", "--help" },
		description = "справка",
		help = true
	)
	boolean help = false;

}
