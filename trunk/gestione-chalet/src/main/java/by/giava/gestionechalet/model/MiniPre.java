package by.giava.gestionechalet.model;


import java.io.Serializable;

public class MiniPre implements Serializable {
	private Long id;
	private String nome;
	private boolean occupato;
	private String kmFatti;

	public String getStyleClass() {
		return getOccupato() ? "occupato" : "libero";
	}

	public String getSymbol() {
		return getOccupato() ? "-X-" : "-O-";
	}

	public MiniPre(Long id, String nome, String kmFatti) {
		this.id = id;
		this.nome = nome;
		this.occupato = false;
		this.kmFatti = kmFatti;
	}

	public MiniPre(Long id, String nome, Boolean occupato, String kmFatti) {
		this.id = id;
		this.nome = nome;
		this.occupato = occupato;
		this.kmFatti = kmFatti;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean getOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}

	public String getKmFatti() {
		return kmFatti;
	}

	public void setKmFatti(String kmFatti) {
		this.kmFatti = kmFatti;
	}
}
