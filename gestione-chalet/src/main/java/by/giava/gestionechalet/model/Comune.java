package by.giava.gestionechalet.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Comune implements Serializable {

	private Long id;

	private String comune;

	private String provincia;
	
	private Long id_pro;

	private List<String> cap;

	public List<String> getCap() {
		return cap;
	}

	public void setCap(List<String> cap) {
		this.cap = cap;
	}

	public void addCap(String cap) {
		if (this.cap == null) {
			this.cap = new ArrayList<String>();
		}
		this.cap.add(cap);
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

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

	public Long getId_pro() {
		return id_pro;
	}

	public void setId_pro(Long id_pro) {
		this.id_pro = id_pro;
	}

}
