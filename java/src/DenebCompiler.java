import java.io.*;

import javax.xml.parsers.ParserConfigurationException;

import org.deneblingvo.language.*;
import org.deneblingvo.notation.*;
import org.deneblingvo.parser.*;
import org.xml.sax.SAXException;

import com.beust.jcommander.*;

public class DenebCompiler {
	
	public static void main(java.lang.String[] args) throws ArrayIndexOutOfBoundsException, ENotSupportedNotationable, ParserConfigurationException, SAXException, IOException {
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
