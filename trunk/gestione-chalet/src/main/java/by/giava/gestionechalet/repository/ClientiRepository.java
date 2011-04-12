package by.giava.gestionechalet.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import by.giava.gestionechalet.model.Cliente;

@Named
@RequestScoped
public class ClientiRepository extends BaseRepository<Cliente> {

	private static final long serialVersionUID = 1L;

	public List<Cliente> getAllClienti() {
		List<Cliente> result = new ArrayList<Cliente>();
		try {
			result = em.createQuery(
					"select t from Cliente t order by t.cognome, t.nome")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}

	@Override
	protected String getDefaultOrderBy() {
		return "cognome asc";
	}
}
