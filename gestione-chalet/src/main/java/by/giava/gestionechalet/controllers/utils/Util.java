package by.giava.gestionechalet.controllers.utils;

import it.coopservice.commons2.utils.JSFUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

import by.giava.gestionechalet.controllers.PropertiesHandler;
import by.giava.gestionechalet.model.Cliente;

public class Util {

	static Logger log = Logger.getLogger(Util.class.getName());

	public static String getArrayValue(String name, Long id) {
		ArrayList array = null;
		if (id < 1) {
			id = new Long(1);
		}
		try {
			if (name.compareTo("Nazioni") == 0) {
				return (String) ((PropertiesHandler) getManagedBean("propertiesHandler"))
						.getNazioni().get(id).getNazione();
			} else if (name.compareTo("Province") == 0) {
				return (String) ((PropertiesHandler) getManagedBean("propertiesHandler"))
						.getProvince().get(id).getProvincia();
			} else if (name.compareTo("Comuni") == 0) {
				return (String) ((PropertiesHandler) getManagedBean("propertiesHandler"))
						.getComuni().get(id).getComune();
			}
		} catch (Exception e) {
			log.info("it.somaro.web.Util.getArrayValue(" + name + ", " + id
					+ "), array = " + array);
			e.printStackTrace();
			return "";
		}
		return "";
	}

	public static String getArrayValue(String name, Long id, FacesContext fc) {
		if (name.compareTo("Nazioni") == 0) {
			return (String) ((PropertiesHandler) JSFUtils
					.getManagedBean("propertiesHandler")).getNazioni().get(id)
					.getNazione();
		} else if (name.compareTo("Province") == 0) {
			return (String) ((PropertiesHandler) JSFUtils
					.getManagedBean("propertiesHandler")).getProvince().get(id)
					.getProvincia();
		} else if (name.compareTo("Comuni") == 0) {
			return (String) ((PropertiesHandler) JSFUtils
					.getManagedBean("propertiesHandler")).getComuni().get(id)
					.getComune();
		} else {
			ArrayList<Object> array = (ArrayList<Object>) fc.getApplication()
					.getELResolver().getValue(fc.getELContext(), null, name);
			return (String) array.get(id.intValue());
		}

	}

	public static Map getMap(String mapName, FacesContext fc) {
		return (HashMap) fc.getApplication().getELResolver().getValue(
				fc.getELContext(), null, mapName);
	}

	public static List getArray(String name, FacesContext fc) {
		return (ArrayList) fc.getApplication().getELResolver().getValue(
				fc.getELContext(), null, name);
	}

	public static Object getParameter(String name) {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getExternalContext().getRequestParameterMap().get(name);
	}

	public static String getCurrentPage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest httpRequest = (HttpServletRequest) fc
				.getExternalContext().getRequest();
		return httpRequest.getRequestURI();
	}

	public static String getContextPath() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String cp = fc.getExternalContext().getRequestContextPath();
		return cp;
	}

	public static String getAbsolutePath() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest httpServletRequest = (HttpServletRequest) fc
				.getExternalContext().getRequest();
		String scheme = httpServletRequest.getScheme();
		String hostName = httpServletRequest.getServerName();
		int port = httpServletRequest.getServerPort();
		// Because this is rendered in a <div> layer, portlets for some reason
		// need the scheme://hostname:port part of the URL prepended.
		return scheme + "://" + hostName + ":" + port + getContextPath();
	}

	public static String getSessionId() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest httpRequest = (HttpServletRequest) fc
				.getExternalContext().getRequest();
		HttpSession session = httpRequest.getSession();
		String id = session.getId();
		log.info("Session Id is : " + id);
		return id;
	}

	public static Object getManagedBean(String name) {
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc == null) {
			System.out.println("Faces Context Application NULLO");
		}
		return fc.getApplication().getELResolver().getValue(fc.getELContext(),
				null, name);
		// return fc.getApplication().getVariableResolver().resolveVariable(fc,
		// name);
	}

	public static void valorizzaCliente(Cliente cliente) {
		cliente.setProvinciaName(Util.getArrayValue("Province", cliente
				.getProvincia()));
		if (cliente.getProvincia() != 109) {
			cliente
					.setCityName(Util
							.getArrayValue("Comuni", cliente.getCity()));
		}

		cliente.setNazioneName(Util.getArrayValue("Nazioni", cliente
				.getNazione()));

		cliente.setProvinciaNascitaName(Util.getArrayValue("Province", cliente
				.getProvinciaNascita()));
		if (cliente.getProvinciaNascita() != 109) {
			cliente.setCityNascitaName(Util.getArrayValue("Comuni", cliente
					.getCityNascita()));
		}

		if (cliente.getCap() == 0) {
			cliente.setCap(null);
		}

		cliente.setNazioneNascitaName(Util.getArrayValue("Nazioni", cliente
				.getNazioneNascita()));
	}

}
