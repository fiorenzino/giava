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

import by.giava.gestionechalet.model.Tariffa;
import by.giava.gestionechalet.repository.TariffeRepository;

@SessionScoped
@Named
public class TariffeController extends AbstractController<Tariffa> {

	private Tariffa tariffa;

	private SelectItem[] tariffeItems;

	@Inject
	TariffeRepository tariffeRepository;

	@Override
	public Repository<Tariffa> getRepository() {
		// TODO Auto-generated method stub
		return tariffeRepository;
	}

	public SelectItem[] getTariffeList() {
		if (tariffeItems == null) {
			List<Tariffa> tariffe = tariffeRepository.getAllTariffe();
			SelectItem[] items = new SelectItem[tariffe.size()];
			int i = 0;
			for (Tariffa tariffa : tariffe) {
				items[i++] = new SelectItem(tariffa.getId(), tariffa.getNome());
			}
			tariffeItems = items;
		}
		return tariffeItems;
	}

	public String addTariffa1() {
		super.setEditMode(false);
		this.tariffa = new Tariffa();
		return "/tariffe/gestione-tariffa.xhtml";
	}

	public String addTariffa2() {
		tariffeRepository.persist(this.tariffa);
		tariffeItems = null;
		refreshModel();
		return "/tariffe/scheda-tariffa.xhtml";
	}

	public String modTariffa1() {
		this.tariffa = (Tariffa) getModel().getRowData();
		super.setEditMode(true);
		return "/tariffe/gestione-tariffa.xhtml";
	}

	public String modTariffa2() {
		tariffeRepository.update(this.tariffa);
		tariffeItems = null;
		refreshModel();
		return "/tariffe/scheda-tariffa.xhtml";
	}

	public String delTariffa() {
		tariffeRepository.update(this.tariffa);
		tariffeItems = null;
		refreshModel();
		return "/tariffe/scheda-tariffa.xhtml";
	}

	public String detailTariffa() {
		this.tariffa = (Tariffa) getModel().getRowData();
		return "/tariffe/scheda-tariffa.xhtml";
	}

	public Tariffa getTariffa() {
		if (this.tariffa == null)
			this.tariffa = new Tariffa();
		return tariffa;
	}

	public void setTariffa(Tariffa tariffa) {
		this.tariffa = tariffa;
	}

}
