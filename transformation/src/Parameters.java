

import com.beust.jcommander.Parameter;
import java.io.File;
import org.deneblingvo.utils.FileConverter;

public class Parameters {
	
	@Parameter(
		names ={ "-f", "--file"},
		description = "имя файла",
		converter = FileConverter.class
	)
	public File file;

	@Parameter(
		names = { "-h", "--help" },
		description = "справка",
		help = true
	)
	boolean help = false;

	@Parameter(
		names = { "-d", "--debug" },
		description = "отладка",
		help = true
	)
	public boolean debug = false;

}
