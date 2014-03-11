

import com.beust.jcommander.Parameter;
import java.io.File;
import org.deneblingvo.utils.FileConverter;

/**
 * @author Алексей Кляузер <drum@pisem.net>
 * Параметры парсера
 */
public class Parameters {

	@Parameter(
		names ={ "-f", "--file"},
		description = "имя файла",
		converter = FileConverter.class,
		required = true
	)
	public File file;

	@Parameter(
		names ={ "-n", "--notation"},
		description = "нотация",
		converter = FileConverter.class,
		required = true
	)
	public File notation;

	@Parameter(
		names ={ "-x", "--xml"},
		description = "xml нотация"
	)
	public boolean xml = false;

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
