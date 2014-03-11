import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import net.sf.saxon.xpath.XPathFactoryImpl;

import org.deneblingvo.parser.Definition;
import org.deneblingvo.parser.Notation;
import org.deneblingvo.serialization.xml.Reader;
import org.xml.sax.SAXException;

import com.beust.jcommander.JCommander;

/**
 * @author Алексей Кляузер <drum@pisem.net>
 * Разбор файлов на основе нотации
 */
public class DenebParser {

	/**
	 * @param args Аргументы командной строки
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws XPathExpressionException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		Parameters parameters = new Parameters();
		JCommander jc = new JCommander(parameters);
		jc.setProgramName("DenebParser");
		jc.parse(args);

		InputStream source = new FileInputStream(parameters.notation);
		Reader reader = new Reader(new XPathFactoryImpl());
		Notation notation = new Notation();
		reader.read(source, notation);
		for (Definition definition : notation.definitions) {
			System.out.println(definition.lexeme);
		}

	}

}
