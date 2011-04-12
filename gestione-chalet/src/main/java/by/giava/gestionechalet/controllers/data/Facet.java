package by.giava.gestionechalet.controllers.data;

import java.io.Serializable;
import java.util.Date;

public class Facet implements Serializable {
	private String header;
	private Date data;

	/**
	 * TODO Description goes here.
	 * 
	 * @param header
	 * @param footer
	 */
	public Facet(String header, Date data) {
		super();
		this.header = header;
		this.data = data;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
