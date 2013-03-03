import ru.myweek_end.deneb.language.*;
import ru.myweek_end.deneb.notation.*;
import ru.myweek_end.deneb.parser.*;
import java.io.*;
import com.beust.jcommander.*;

public class DenebCompiler {
	
	public static void main(java.lang.String[] args) throws FileNotFoundException, ArrayIndexOutOfBoundsException {
		DenebCompilerParameters parameters = new DenebCompilerParameters();
		JCommander jc = new JCommander(parameters);
		jc.parse(args);
		Notationable notation;
		notation = new Notation();
		Parser parser;
		parser = new Parser(notation);
		FileInputStream source;
		if (parameters.file.isFile()) {
			source = new FileInputStream(parameters.file);
			parser.Parse(source);
		} else {
			jc.setProgramName("DenebCompiler");
			jc.usage();
		}
	}

}
