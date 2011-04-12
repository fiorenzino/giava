package by.giava.gestionechalet.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tariffa implements Serializable {

	private Long id;
	private String nome;
	private Float d1;
	private Float d2;
	private Float d3;
	private Float d4;
	private Float d5;
	private Float d7;
	private Float d30;
	private Float d1ex;

	private Long maxKm;
	private Float costoKm;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMaxKm() {
		return maxKm;
	}

	public void setMaxKm(Long maxKm) {
		this.maxKm = maxKm;
	}

	public Float getCostoKm() {
		return costoKm;
	}

	public void setCostoKm(Float costoKm) {
		this.costoKm = costoKm;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getD1() {
		return d1;
	}

	public void setD1(Float d1) {
		this.d1 = d1;
	}

	public Float getD2() {
		return d2;
	}

	public void setD2(Float d2) {
		this.d2 = d2;
	}

	public Float getD3() {
		return d3;
	}

	public void setD3(Float d3) {
		this.d3 = d3;
	}

	public Float getD4() {
		return d4;
	}

	public void setD4(Float d4) {
		this.d4 = d4;
	}

	public Float getD5() {
		return d5;
	}

	public void setD5(Float d5) {
		this.d5 = d5;
	}

	public Float getD7() {
		return d7;
	}

	public void setD7(Float d7) {
		this.d7 = d7;
	}

	public Float getD30() {
		return d30;
	}

	public void setD30(Float d30) {
		this.d30 = d30;
	}

	public Float getD1ex() {
		return d1ex;
	}

	public void setD1ex(Float d1ex) {
		this.d1ex = d1ex;
	}
}
