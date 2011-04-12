package by.giava.gestionechalet.producers;

import java.io.Serializable;
import java.sql.Connection;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

import org.jboss.seam.solder.core.ExtensionManaged;

import by.giava.gestionechalet.annotations.Ds;

public class EmProducer implements Serializable {

	private static final long serialVersionUID = 1L;

	@ExtensionManaged
	@Produces
	@PersistenceUnit(unitName = "pu")
	@ConversationScoped
	EntityManagerFactory em;

	public EmProducer() {
		System.out.println("start em producer");
	}

	@Produces
	@Ds
	public DataSource getConnection() throws Exception {

		Context ctx = new InitialContext();
		// Read the data source name from web.xml
		String name = "java:/comp/env/jdbc/scooter";
		DataSource ds = (DataSource) ctx.lookup(name);
		return ds;
	}
}
