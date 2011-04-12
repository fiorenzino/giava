package by.giava.gestionechalet.controllers;

import it.coopservice.commons2.annotations.DataRepository;
import it.coopservice.commons2.annotations.OwnRepository;
import it.coopservice.commons2.controllers.AbstractController;
import it.coopservice.commons2.repository.Repository;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import by.giava.gestionechalet.model.Scooter;
import by.giava.gestionechalet.model.Tariffa;
import by.giava.gestionechalet.repository.ScooterRepository;
import by.giava.gestionechalet.repository.TariffeRepository;

@SessionScoped
@Named
public class ScootersController extends AbstractController<Scooter> {

	private Scooter scooter;

	private SelectItem[] scootersItems;

	private SelectItem[] cilindrateItems;

	@Inject
	ScooterRepository scooterRepository;

	@Inject
	TariffeRepository tariffeRepository;

	@Override
	public Repository<Scooter> getRepository() {
		// TODO Auto-generated method stub
		return scooterRepository;
	}

	public SelectItem[] getCilindrateItems() {
		if (cilindrateItems == null) {
			List<String> cilindrate = scooterRepository.getCilindrate();
			SelectItem[] items = new SelectItem[cilindrate.size() + 1];
			items[0] = new SelectItem(0, "scegli");
			int i = 1;
			for (String cil : cilindrate) {
				items[i++] = new SelectItem(cil);
			}
			cilindrateItems = items;
		}
		return cilindrateItems;
	}

	public SelectItem[] getScootersItems() {
		if (scootersItems == null) {
			List<Scooter> scooters = scooterRepository.getAllScooter();
			SelectItem[] items = new SelectItem[scooters.size() + 1];
			items[0] = new SelectItem(0, "scegli");
			int i = 1;
			for (Scooter scooter : scooters) {
				items[i++] = new SelectItem(scooter.getId(), scooter.getNome()
						+ " - " + scooter.getMarcaModello());
			}
			scootersItems = items;
		}
		return scootersItems;
	}

	public String addScooter1() {
		super.setEditMode(false);
		this.scooter = new Scooter();
		return "/scooters/gestione-scooter.xhtml";
	}

	public String addScooter2() {
		Tariffa tariffa = tariffeRepository.find(this.scooter.getTariffa()
				.getId());
		this.scooter.setTariffa(tariffa);
		scooterRepository.persist(this.scooter);
		refreshModel();
		this.scootersItems = null;
		this.cilindrateItems = null;
		return "/scooters/scheda-scooter.xhtml";
	}

	public String modScooter1() {
		this.scooter = (Scooter) getModel().getRowData();
		super.setEditMode(true);
		return "/scooters/gestione-scooter.xhtml";
	}

	public String modScooter2() {
		Tariffa tariffa = tariffeRepository.find(this.scooter.getTariffa()
				.getId());
		this.scooter.setTariffa(tariffa);
		scooterRepository.update(this.scooter);
		refreshModel();
		this.scootersItems = null;
		this.cilindrateItems = null;
		return "/scooters/scheda-scooter.xhtml";
	}

	public String delScooter() {
		scooterRepository.update(this.scooter);
		refreshModel();
		this.scootersItems = null;
		this.cilindrateItems = null;
		return "/scooters/scheda-scooter.xhtml";
	}

	public String detailScooter() {
		this.scooter = (Scooter) getModel().getRowData();
		return "/scooters/scheda-scooter.xhtml";
	}

	public Scooter getScooter() {
		if (this.scooter == null)
			this.scooter = new Scooter();
		return scooter;
	}

	public void setScooter(Scooter scooter) {
		this.scooter = scooter;
	}

}
