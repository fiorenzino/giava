package by.giava.gestionechalet.controllers.print;


import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.lowagie.text.DocumentException;

public class RendererFilter implements Filter {

	FilterConfig config;
	private DocumentBuilder documentBuilder;

	public void init(FilterConfig config) throws ServletException {
		System.setProperty("xr.util-logging.loggingEnabled", "false");

		try {
			this.config = config;
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setValidating(false);
			documentBuilder = factory.newDocumentBuilder();
			documentBuilder.setEntityResolver(new LocalHostEntityResolver());

		} catch (ParserConfigurationException e) {
			throw new ServletException(e);
		}
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		System.out.println("PRINTER PDF");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// Check to see if this filter should apply.
		String renderType = request.getParameter("RenderOutputType");
		// if (renderType != null) {
		// Capture the content for this request
		ContentCaptureServletResponse capContent = new ContentCaptureServletResponse(
				response);
		filterChain.doFilter(request, capContent);

		try {
			// Parse the XHTML content to a document that is readable by the
			// XHTML renderer.
			StringReader contentReader = new StringReader(capContent
					.getContent());
			InputSource source = new InputSource(contentReader);
			Document xhtmlContent = documentBuilder.parse(source);

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(xhtmlContent, "");
			renderer.layout();

			response.setContentType("application/pdf");
			OutputStream browserStream = response.getOutputStream();
			renderer.createPDF(browserStream);
			return;

		} catch (SAXException e) {
			throw new ServletException(e);
		} catch (DocumentException e) {
			throw new ServletException(e);
		}

		// } else {
		// // Normal processing
		// filterChain.doFilter(request, response);
		// }
	}

	public void destroy() {
	}
}
