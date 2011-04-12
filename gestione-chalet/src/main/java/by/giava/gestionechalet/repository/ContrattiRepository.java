package by.giava.gestionechalet.repository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import by.giava.gestionechalet.model.Contratto;
import by.giava.gestionechalet.model.Prenotazione;

@Named
@RequestScoped
public class ContrattiRepository extends BaseRepository<Contratto> {

	private static final long serialVersionUID = 1L;

	public Long getNumContrattiCliente(Long id) {
		Long numContratti = new Long(0);
		try {
			numContratti = (Long) em
					.createNamedQuery("GET_NUM_CONTRATTI_BY_CLIENTE")
					.setParameter("CLIENTE_ID", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numContratti;
	}

	public Contratto updateSpecial(Contratto contratto) {
		try {
			Contratto oldContratto = em
					.find(Contratto.class, contratto.getId());
			for (Prenotazione prenotazione : oldContratto.getPrenotazioni()) {
				em.remove(prenotazione);
			}
			oldContratto = contratto;
			em.merge(oldContratto);
			return oldContratto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected String getDefaultOrderBy() {
		// TODO Auto-generated method stub
		return "id desc";
	}

}
