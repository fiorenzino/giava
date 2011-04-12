package by.giava.gestionechalet.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import by.giava.gestionechalet.annotations.Ds;
import by.giava.gestionechalet.model.Scooter;

@Named
@RequestScoped
public class ScooterRepository extends BaseRepository<Scooter> {

	private static final long serialVersionUID = 1L;

	@Inject
	@Ds
	private DataSource dataSource;


	public List<Scooter> getAllScooter() {
		List<Scooter> result = new ArrayList<Scooter>();
		try {
			result = em.createQuery(
					"select t from Scooter t order by t.marca, t.modello")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}

	public List<String> getCilindrate() {
		List<String> result = new ArrayList<String>();
		try {
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String query = "select distinct(cilindrata) as cil from Scooter order by cilindrata asc";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				result.add(rs.getString("cil"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	protected String getDefaultOrderBy() {
		// TODO Auto-generated method stub
		return "marca";
	}

}
