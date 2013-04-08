import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.deneblingvo.transformator.Parameters;
import org.deneblingvo.transformator.Transformation;
import org.deneblingvo.xml.Reader;

import com.beust.jcommander.JCommander;


public class DenebTransformator {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
		Parameters parameters = new Parameters();
		JCommander jc = new JCommander(parameters);
		jc.parse(args);

		jc.setProgramName("DenebCompiler");
		jc.usage();
		
		InputStream source;
		if (parameters.file.isFile()) {
			source = new FileInputStream(parameters.file);
		} else {
			source = System.in;
		}
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(source);
		
		Transformation transformation = new Transformation();
		Reader reader = new Reader(transformation);
		reader.parse(document);
	}

}
