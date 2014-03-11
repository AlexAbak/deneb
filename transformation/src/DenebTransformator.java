import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.sf.saxon.s9api.SaxonApiException;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

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
	 * @throws SaxonApiException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, XPathExpressionException, TransformerException, SaxonApiException {
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		Parameters parameters = new Parameters();
		JCommander jc = new JCommander(parameters);
		jc.parse(args);
		jc.setProgramName("DenebCompiler");
		
		Transformator transformator = new Transformator();
		InputStream source;
		if ((parameters.file != null) && (parameters.file.isFile())) {
			String curent_dir = parameters.file.getAbsoluteFile().getParent();
			System.setProperty("user.dir", curent_dir);
			source = new FileInputStream(parameters.file);
			transformator.transformate(parameters.debug, source);
			source.close();
		} else {
			jc.usage();
			source = System.in;
			transformator.transformate(parameters.debug, source);
		}
	}

}
