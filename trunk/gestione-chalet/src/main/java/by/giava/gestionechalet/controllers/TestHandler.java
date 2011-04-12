package by.giava.gestionechalet.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import by.giava.gestionechalet.model.Contratto;
import by.giava.gestionechalet.model.MiniPre;
import by.giava.gestionechalet.model.Scooter;
import by.giava.gestionechalet.repository.PrenotazioniRepository;

@SessionScoped
@Named
public class TestHandler implements Serializable {

	private List<MiniPre[]> model;
	private List<String> colonne;
	private Date dal;
	private Date al;
	private int begin;
	private int end;
	private String cil;

	private Scooter scooter;
	private Contratto contratto;

	@Inject
	PrenotazioniRepository prenotazioniRepository;

	public List<MiniPre[]> getModel() {
		Map<Long, Map<String, MiniPre>> mappa = prenotazioniRepository
				.getReservationList(getDal(), getAl(), getCil());
		model = new ArrayList<MiniPre[]>();
		for (Long nome : mappa.keySet()) {
			MiniPre[] sco = new MiniPre[getColonne().size() + 1];

			Map<String, MiniPre> occ = mappa.get(nome);
			int l = 1;
			for (String key : occ.keySet()) {
				if (l == 1) {
					sco[0] = occ.get(key);
				}
				sco[l++] = occ.get(key);
			}
			model.add(sco);
		}

		// for (int m = 0; m < 3; m++) {
		// String[] sco = new String[getColonne().size() + 1];
		// sco[0] = "scooter" + m;
		// for (int i = 1; i <= getColonne().size(); i++) {
		// sco[i] = "libero";
		// if (i % 4 == 0)
		// sco[i] = "occupato";
		// }
		// model.add(sco);
		// }
		return model;
	}

	public void setModel(List<MiniPre[]> model) {
		this.model = model;
	}

	public List<String> getColonne() {
		if (colonne == null)
			aggColonne();
		return colonne;
	}

	public void setColonne(List<String> colonne) {
		this.colonne = colonne;
	}

	public void aggColonne() {
		colonne = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDal());
		colonne.add("scooter");
		while (cal.getTime().compareTo(getAl()) <= 0) {
			// System.out.println("COLDATA: " + cal.get(Calendar.DAY_OF_MONTH)
			// + "-" + cal.get(Calendar.MONTH) + "-"
			// + cal.get(Calendar.YEAR));
			colonne.add(cal.get(Calendar.DAY_OF_MONTH) + "-"
					+ cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR));
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
	}

	public Date getDal() {
		if (dal == null) {
			dal = new Date();
		}
		return dal;
	}

	public void setDal(Date dal) {
		this.dal = dal;
	}

	public Date getAl() {
		if (al == null) {
			Calendar calA = Calendar.getInstance();
			calA.setTime(getDal());
			calA.add(Calendar.DAY_OF_MONTH, 6);
			al = calA.getTime();

		}
		return al;
	}

	public void setAl(Date al) {
		this.al = al;
	}

	public int getBegin() {
		return 0;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return getColonne().size() + 1;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getCil() {
		return cil;
	}

	public void setCil(String cil) {
		this.cil = cil;
	}

	public Scooter getScooter() {
		if (scooter == null)
			this.scooter = new Scooter();
		return scooter;
	}

	public void setScooter(Scooter scooter) {
		this.scooter = scooter;
	}

}
