import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.deneblingvo.transformator.Parameters;
import org.deneblingvo.transformator.Transformator;

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
	 * @throws XPathExpressionException 
	 * @throws TransformerException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, XPathExpressionException, TransformerException {
		Parameters parameters = new Parameters();
		JCommander jc = new JCommander(parameters);
		jc.parse(args);

		jc.setProgramName("DenebCompiler");
		jc.usage();
		
		Transformator transformator = new Transformator();
		InputStream source;
		if ((parameters.file != null) && (parameters.file.isFile())) {
			source = new FileInputStream(parameters.file);
			transformator.transformate(source);
			source.close();
		} else {
			source = System.in;
			transformator.transformate(source);
		}
	}

}
