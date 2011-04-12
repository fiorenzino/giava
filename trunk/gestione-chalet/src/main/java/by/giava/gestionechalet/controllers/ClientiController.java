package by.giava.gestionechalet.controllers;

import it.coopservice.commons2.annotations.OwnRepository;
import it.coopservice.commons2.controllers.AbstractController;
import it.coopservice.commons2.repository.Repository;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import by.giava.gestionechalet.controllers.utils.Util;
import by.giava.gestionechalet.model.Cliente;
import by.giava.gestionechalet.repository.ClientiRepository;
import by.giava.gestionechalet.repository.ContrattiRepository;

@Named
@ConversationScoped
public class ClientiController extends AbstractController<Cliente> {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;

	@Inject
	ClientiRepository clientiRepository;

	@Inject
	ContrattiRepository contrattiRepository;

	private SelectItem[] clientiItems;

	@Override
	public Repository<Cliente> getRepository() {
		return clientiRepository;
	}

	public SelectItem[] getClientiItems() {
		if (clientiItems == null) {
			List<Cliente> clienti = clientiRepository.getAllClienti();
			SelectItem[] items = new SelectItem[clienti.size() + 1];
			items[0] = new SelectItem(0, "scegli");
			int i = 1;
			for (Cliente cliente : clienti) {
				items[i++] = new SelectItem(cliente.getId(),
						cliente.getNomeCognome());
			}
			clientiItems = items;
		}
		return clientiItems;
	}

	public String addCliente1() {
		this.cliente = new Cliente();
		return "/clienti/gestione-cliente.xhtml";
	}

	public String addCliente2() {
		this.cliente.setDataInsert(new Date());
		clientiRepository.persist(this.cliente);
		refreshModel();
		Util.valorizzaCliente(cliente);
		clientiItems = null;
		return "/clienti/scheda-cliente.xhtml";
	}

	public String modCliente1() {
		this.cliente = (Cliente) getModel().getRowData();
		super.setEditMode(true);
		return "/clienti/gestione-cliente.xhtml";
	}

	public String modCliente2() {
		clientiRepository.update(this.cliente);
		refreshModel();
		Util.valorizzaCliente(cliente);
		clientiItems = null;
		return "/clienti/scheda-cliente.xhtml";
	}

	public String delCliente() {
		Long numContratti = contrattiRepository
				.getNumContrattiCliente(this.cliente.getId());
		if (numContratti < 1) {
			clientiRepository.delete(this.cliente);
		}

		refreshModel();
		clientiItems = null;
		return "/clienti/clienti.xhtml";
	}

	public String detailCliente() {
		this.cliente = (Cliente) getModel().getRowData();
		Util.valorizzaCliente(cliente);
		return "/clienti/scheda-cliente.xhtml";
	}

	public Cliente getCliente() {
		if (cliente == null)
			this.cliente = new Cliente();
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
