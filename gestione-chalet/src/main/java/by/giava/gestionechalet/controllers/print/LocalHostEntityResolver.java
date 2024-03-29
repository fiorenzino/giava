package by.giava.gestionechalet.controllers.print;


import java.net.URL;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LocalHostEntityResolver implements EntityResolver {

	private static final String XHTML1_TRANSITIONAL_DTD = "xhtml1-transitional.dtd";

	public InputSource resolveEntity(String publicID, String systemID)
			throws SAXException {
		if ("-//W3C//DTD XHTML 1.0 Transitional//EN".equals(publicID)
				|| "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
						.equals(systemID)) {
			// extract Resource from facelets.jar
			URL url = Thread.currentThread().getContextClassLoader()
					.getResource(XHTML1_TRANSITIONAL_DTD);
			return new InputSource(url.toString());
		}
		// If no match, returning null makes process continue normally
		return null;
	}

}
