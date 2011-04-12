package by.giava.gestionechalet.repository;

import it.coopservice.commons2.repository.AbstractRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * BaseRepository
 * 
 * Unified {@link EntityManager} injection point.
 * 
 * @param <T>
 */
public abstract class BaseRepository<T> extends AbstractRepository<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// --- JPA ---------------------------------

	@Inject
	EntityManager em;

	@Override
	protected EntityManager getEm() {
		return em;
	}

	@Override
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
