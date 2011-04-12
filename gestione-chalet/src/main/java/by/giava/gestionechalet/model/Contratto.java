package by.giava.gestionechalet.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import by.giava.gestionechalet.controllers.utils.TimeUtils;

@Entity
public class Contratto implements Serializable {

	private Long id;
	private Date dataStipula;
	private Date dataInit;
	private Date dataEnd;
	private Date dataRiconsegna;
	private Cliente cliente;
	private Scooter scooter;
	private String note;
	private Float importoIniziale;
	private Float importoFinale;
	private Float importokmExtra;
	private Float importoRitiroMezzo;
	private Float importoSottoCasco;
	private Float importoCaparra;
	private Float importoDanni;
	private Float extra;
	private Float totale;
	private Float totaleParziale;
	private Float totaleChiusura;
	private Float benzinaExtra;
	private Float cascoExtra;
	private Float kmExtra;
	private String kmIniziali;
	private String kmFinali;
	private Float sconto;
	private Boolean aperto;
	private List<Prenotazione> prenotazioni;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInit() {
		if (dataInit == null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			dataInit = cal.getTime();
		}
		return dataInit;
	}

	public void setDataInit(Date dataInit) {
		this.dataInit = dataInit;
	}

	public Date getDataEnd() {
		if (dataEnd == null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(getDataInit());
			cal.add(Calendar.DAY_OF_MONTH, 6);
			dataEnd = cal.getTime();
		}
		return dataEnd;
	}

	public void setDataEnd(Date dataEnd) {
		this.dataEnd = dataEnd;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Cliente getCliente() {
		if (this.cliente == null)
			this.cliente = new Cliente();
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Lob
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Float getImportoIniziale() {
		if (importoIniziale == null)
			importoIniziale = new Float(0);
		return importoIniziale;
	}

	public void setImportoIniziale(Float importoIniziale) {
		this.importoIniziale = importoIniziale;
	}

	public Float getImportoFinale() {
		if (importoFinale == null)
			importoFinale = new Float(0);
		return importoFinale;
	}

	public void setImportoFinale(Float importoFinale) {
		this.importoFinale = importoFinale;
	}

	@Transient
	public Long getNumGiorniTotal() {
		try {
			return TimeUtils.getDiffDays(getDataInit(), getDataRiconsegna());

		} catch (Exception e) {
			e.printStackTrace();
			return new Long(0);
		}

	}

	@Transient
	public Long getNumGiorniExtra() {
		try {
			Long tot = TimeUtils.getDiffDays(getDataInit(), getDataRiconsegna());
			Long ini = TimeUtils.getDiffDays(getDataInit(), getDataEnd());
			return tot - ini;

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("getNumGiorniExtra ERROR");
			return new Long(0);
		}

	}

	@Transient
	public Float getExtra() {
		if (extra == null)
			extra = new Float(0);
		try {
			return getNumGiorniExtra() * getScooter().getTariffa().getD1ex();

		} catch (Exception e) {
			System.out.println("getExtra ERROR");
		}
		return new Float(0);
	}

	public void setExtra(Float extra) {
		this.extra = extra;
	}

	@Transient
	public Float getTotale() {
		try {
			if (getSconto() != 0) {
				return (getImportoIniziale() - (getSconto()
				// * getImportoIniziale() / new Float(100)
						))
						+ getImportoFinale() + getExtra()
						+ getBenzinaExtra()
						+ getCascoExtra()
						+ getImportokmExtra()
						+ getImportoDanni()
						+ getImportoRitiroMezzo()
						+ getImportoSottoCasco();
			}
			return getImportoIniziale() + getImportoFinale() + getExtra()
					+ getBenzinaExtra() + getCascoExtra() + getImportokmExtra()
					+ getImportoDanni() + getImportoRitiroMezzo()
					+ getImportoSottoCasco();
		} catch (Exception e) {
			System.out.println("getTotale ERROR");
		}
		return new Float(0);

	}

	public void setTotale(Float totale) {
		this.totale = totale;
	}

	@Transient
	public Float getTotaleParziale() {
		if (getSconto() != 0) {
			return (getImportoIniziale() - (getSconto()
			// * getImportoIniziale() / new Float(100)
					))
					+ getCascoExtra()
					+ getImportoCaparra()
					+ getImportoSottoCasco();
		}
		return getImportoIniziale() + getCascoExtra() + getImportoCaparra()
				+ getImportoSottoCasco();
	}

	public void setTotaleParziale(Float totaleParziale) {
		this.totaleParziale = totaleParziale;
	}

	@Transient
	public Float getTotaleChiusura() {
		return getTotale() - getImportoCaparra();
	}

	public void setTotaleChiusura(Float totaleChiusura) {
		this.totaleChiusura = totaleChiusura;
	}

	@OneToMany(mappedBy = "contratto", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	public List<Prenotazione> getPrenotazioni() {
		if (prenotazioni == null)
			prenotazioni = new ArrayList<Prenotazione>();
		return prenotazioni;
	}

	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Scooter getScooter() {
		if (this.scooter == null)
			this.scooter = new Scooter();
		return scooter;
	}

	public void setScooter(Scooter scooter) {
		this.scooter = scooter;
	}

	public Float getSconto() {
		if (sconto == null)
			sconto = new Float(0);
		return sconto;
	}

	public void setSconto(Float sconto) {
		this.sconto = sconto;
	}

	public Boolean getAperto() {
		return aperto;
	}

	public void setAperto(Boolean aperto) {
		this.aperto = aperto;
	}

	public Date getDataRiconsegna() {
		if (dataRiconsegna == null)
			dataRiconsegna = getDataEnd();
		return dataRiconsegna;
	}

	public void setDataRiconsegna(Date dataRiconsegna) {
		this.dataRiconsegna = dataRiconsegna;
	}

	public Float getBenzinaExtra() {
		if (benzinaExtra == null)
			benzinaExtra = new Float(0);
		return benzinaExtra;
	}

	public void setBenzinaExtra(Float benzinaExtra) {
		this.benzinaExtra = benzinaExtra;
	}

	public Float getCascoExtra() {
		if (cascoExtra == null)
			cascoExtra = new Float(0);
		return cascoExtra;
	}

	public void setCascoExtra(Float cascoExtra) {
		this.cascoExtra = cascoExtra;
	}

	@Transient
	public Long getKmMaxTot() {
		return getScooter().getTariffa().getMaxKm() * getNumGiorniTotal();

	}

	@Transient
	public Float getKmExtra() {
		try {
			Float KmI = Float.parseFloat(kmIniziali);
			Float KmF = Float.parseFloat(kmFinali);
			if ((kmIniziali != null) && (kmFinali != null) && (KmF > KmI)) {
				Long kmMax = getKmMaxTot();
				Float kmFatti = KmF - KmI;
				if ((kmFatti != null) && (kmFatti > 0) && (kmFatti > kmMax))
					return kmFatti - kmMax;
			}

		} catch (Exception e) {
			System.out.println("getKmExtra");

		}
		return new Float(0);

	}

	@Transient
	public Float getImportokmExtra() {
		if (importokmExtra == null)
			importokmExtra = new Float(0);
		importokmExtra = getScooter().getTariffa().getCostoKm() * getKmExtra();
		return importokmExtra;
	}

	public void setImportokmExtra(Float importokmExtra) {
		this.importokmExtra = importokmExtra;
	}

	public Float getImportoRitiroMezzo() {
		if (importoRitiroMezzo == null)
			importoRitiroMezzo = new Float(0);
		return importoRitiroMezzo;
	}

	public void setImportoRitiroMezzo(Float importoRitiroMezzo) {
		this.importoRitiroMezzo = importoRitiroMezzo;
	}

	public Float getImportoSottoCasco() {
		if (importoSottoCasco == null)
			importoSottoCasco = new Float(0);
		return importoSottoCasco;
	}

	public void setImportoSottoCasco(Float importoSottoCasco) {
		this.importoSottoCasco = importoSottoCasco;
	}

	public Float getImportoCaparra() {
		if (importoCaparra == null)
			importoCaparra = new Float(0);
		return importoCaparra;
	}

	public void setImportoCaparra(Float importoCaparra) {
		this.importoCaparra = importoCaparra;
	}

	public Float getImportoDanni() {
		if (importoDanni == null)
			importoDanni = new Float(0);
		return importoDanni;
	}

	public void setImportoDanni(Float importoDanni) {
		this.importoDanni = importoDanni;
	}

	public Date getDataStipula() {
		return dataStipula;
	}

	public void setDataStipula(Date dataStipula) {
		this.dataStipula = dataStipula;
	}

	public String getKmIniziali() {
		return kmIniziali;
	}

	public void setKmIniziali(String kmIniziali) {
		this.kmIniziali = kmIniziali;
	}

	public String getKmFinali() {
		return kmFinali;
	}

	public void setKmFinali(String kmFinali) {
		this.kmFinali = kmFinali;
	}

}
