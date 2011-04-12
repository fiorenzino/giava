package by.giava.gestionechalet.controllers;


import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class TestHandler2 implements Serializable {

	private String body;
	private String header;
	private String footer;
	private String html;

	private boolean withFilter;

	public String creaPdf() {
		return "/print/dinamica.xhtml";
	}

	public String creaPdfCompleto() {
		return "/print/dina-completo.xhtml";
	}

	public String getHtml() {
		if (withFilter == false)
			return html;
		else
			return html.replaceAll("\\{ruolo\\}", "grande ingnegnere");
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public boolean isWithFilter() {
		return withFilter;
	}

	public void setWithFilter(boolean withFilter) {
		this.withFilter = withFilter;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getHeader() {
		if (header == null)
			header = "<div style=\"text-align: center;\">"
					+ "<table style=\"width: 100%;\">"
					+ "<tr>"
					+ "<td><img src=\"../images/logo.jpg\""
					+ "	width=\"88\" height=\"44\" /></td>"
					+ "<td><span"
					+ "	style=\"font-size: 10px; font-family: arial; font-weight: bold;\">Simonelli"
					+ "Moto s.a.s. di Ranalli Guido e Gianfranco - via Silvio Pellico, 198 -"
					+ "63039 - San Benedetto del Tronto (AP) - p.i. 01681320444 telefono"
					+ "+39.073586967 fax +39.0735789187 e-mail: info@simonellimoto.it</span></td>"
					+ "<td><img src=\"../images/guzzi.png\""
					+ "	width=\"75\" height=\"44\" /></td>" + "</tr>"
					+ "</table>" + "</div>";
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFooter() {
		if (footer == null)
			footer = "<div style=\"text-align: center;\"> pag. <span id=\"pagenumber\"/> di <span id=\"pagecount\"/> </div>";
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}
}
