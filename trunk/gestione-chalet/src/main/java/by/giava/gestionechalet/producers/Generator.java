package by.giava.gestionechalet.producers;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import by.giava.gestionechalet.annotations.Columns;
import by.giava.gestionechalet.controllers.PrenotazioniController;
import by.giava.gestionechalet.controllers.data.Facet;

@ApplicationScoped
public class Generator implements Serializable {

	@Inject
	PrenotazioniController prenotazioniController;

	private ArrayList<Facet> columns;

	@Produces
	@Columns
	public ArrayList<Facet> getColumns() {
		prenotazioniController.initColumns();
		return prenotazioniController.getColumns();
	}

}
