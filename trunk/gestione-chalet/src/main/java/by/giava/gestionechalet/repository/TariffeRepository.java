package by.giava.gestionechalet.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import by.giava.gestionechalet.model.Scooter;
import by.giava.gestionechalet.model.Tariffa;

@Named
@ConversationScoped
public class TariffeRepository extends BaseRepository<Tariffa> {

	public List<Tariffa> getAllTariffe() {
		List<Tariffa> result = new ArrayList<Tariffa>();
		try {
			result = em.createQuery("select t from Tariffa t order by t.nome")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}

	public Tariffa getTariffaByCilindrata(String cilindrata) {
		System.out.println("GET SCOOTER BY CILINDRATA");
		List<Scooter> result = new ArrayList<Scooter>();
		try {
			result = em
					.createQuery(
							"select t from Scooter t where t.cilindrata = :CIL")
					.setParameter("CIL", cilindrata).getResultList();
			if (result.size() > 0 && result.get(0) != null)
				return result.get(0).getTariffa();
		} catch (Exception e) {
			System.out.println("GET SCOOTER BY CILINDRATA EXC");
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	protected String getDefaultOrderBy() {
		// TODO Auto-generated method stub
		return "nome";
	}
}
