package by.giava.gestionechalet.controllers.event;


import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import by.giava.gestionechalet.annotations.Created;
import by.giava.gestionechalet.annotations.Updated;

@Named
@SessionScoped
public class NoteObserver implements Serializable {

	public NoteObserver() {
		// TODO Auto-generated constructor stub
	}

	public void afterCreated(@Observes @Created ChangeNoteEvent event) {
		System.out.println("NoteObserver - @Created: " + event.getNote());
	}

	public void afterUpdated(@Observes @Updated ChangeNoteEvent event) {
		System.out.println("NoteObserver - @Updated: " + event.getNote());
	}
}
