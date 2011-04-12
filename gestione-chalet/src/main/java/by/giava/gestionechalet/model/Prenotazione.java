package by.giava.gestionechalet.model;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Prenotazione implements Serializable {

	private Long id;
	private Date singleDay;
	private String singleDayName;
	private Contratto contratto;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSingleDay() {
		return singleDay;
	}

	public void setSingleDay(Date singleDay) {
		this.singleDay = singleDay;
	}

	@Transient
	public String getSingleDayName() {
		if (singleDay != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(singleDay);
			return cal.get(Calendar.DAY_OF_MONTH) + "-"
					+ (cal.get(Calendar.MONTH) + 1) + "-"
					+ cal.get(Calendar.YEAR);
		}
		return "";
	}

	public void setSingleDayName(String singleDayName) {
		this.singleDayName = singleDayName;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

}
