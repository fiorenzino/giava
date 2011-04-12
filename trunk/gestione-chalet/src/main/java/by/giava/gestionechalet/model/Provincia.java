package by.giava.gestionechalet.model;


import java.io.Serializable;

public class Provincia implements Serializable {
	private Long id;
	private String provincia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
}
