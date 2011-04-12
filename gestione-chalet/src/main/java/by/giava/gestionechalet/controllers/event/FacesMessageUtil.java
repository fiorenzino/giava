package by.giava.gestionechalet.controllers.event;


import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "facesMessage")
@Dependent
public class FacesMessageUtil implements Serializable {
	
	public FacesMessageUtil() {

	}

	public void addMessage(Severity severity, String summary, String detail) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
