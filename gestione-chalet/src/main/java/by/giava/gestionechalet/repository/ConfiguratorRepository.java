package by.giava.gestionechalet.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

import by.giava.gestionechalet.annotations.Ds;
import by.giava.gestionechalet.model.Comune;
import by.giava.gestionechalet.model.Nazione;
import by.giava.gestionechalet.model.Provincia;

@Named
@ConversationScoped
public class ConfiguratorRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;

	@Inject
	@Ds
	private DataSource ds;

	Logger log = Logger.getLogger(getClass().getName());

	public TreeMap<Long, Comune> getComuni() {

		TreeMap<Long, Comune> result = new TreeMap<Long, Comune>();
		log.info("vediamo: getComuni");

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt
					.executeQuery("SELECT A.id, B.provincia, B.id as id_pro,  A.comune, C.cap FROM comuni as A LEFT JOIN province as B ON B.id = A.id_pro LEFT JOIN cap as C on C.id_com = A.id order by B.provincia, A.comune ");
			while (rs.next()) {
				if (result.containsKey(rs.getLong("id"))) {
					Comune comune = result.get(rs.getLong("id"));
					comune.addCap(rs.getString("cap"));
				} else {
					Comune comune = new Comune();
					comune.setId(rs.getLong("id"));
					comune.setId_pro(rs.getLong("id_pro"));
					comune.setComune(rs.getString("comune"));
					comune.setProvincia(rs.getString("provincia"));
					comune.addCap(rs.getString("cap"));
					result.put(comune.getId(), comune);
				}

			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) { // Report
			e.printStackTrace();
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		return result;
	}

	public TreeMap<Long, Provincia> getProvince() {

		TreeMap<Long, Provincia> result = new TreeMap<Long, Provincia>();
		log.info("vediamo: getProvince");

		Connection con;
		Statement stmt;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select id, provincia from province order by provincia asc;");
			while (rs.next()) {
				Provincia prov = new Provincia();
				prov.setId(rs.getLong("id"));
				prov.setProvincia(rs.getString("provincia"));
				result.put(prov.getId(), prov);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) { // Report
			e.printStackTrace();
		}

		return result;
	}

	public TreeMap<Long, Nazione> getNazioni() {

		TreeMap<Long, Nazione> result = new TreeMap<Long, Nazione>();
		log.info("vediamo: getNazioni");

		Connection con;
		Statement stmt;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select id, nazione, cc from nazioni order by nazione asc;");
			while (rs.next()) {
				Nazione naz = new Nazione();
				naz.setId(rs.getLong("id"));
				naz.setNazione(rs.getString("nazione"));
				naz.setCc(rs.getString("cc"));
				result.put(naz.getId(), naz);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) { // Report
			e.printStackTrace();
		}

		return result;
	}
}
