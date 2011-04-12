package by.giava.gestionechalet.model;


import java.io.Serializable;

public class Nazione implements Serializable {
	private Long id;
	private String nazione;
	private String cc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

}
