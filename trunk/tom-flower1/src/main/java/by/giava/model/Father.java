package by.giava.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Father")
public class Father implements Serializable {

	private Long id;
	private String name;
	private String surname;
	private List<Son> sons;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Son> getSons() {
		if (this.sons == null)
			this.sons = new ArrayList<Son>();
		return sons;
	}

	public void setSons(List<Son> sons) {
		this.sons = sons;
	}
}
