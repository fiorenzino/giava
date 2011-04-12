package by.giava.gestionechalet.controllers.event;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import by.giava.gestionechalet.annotations.Created;
import by.giava.gestionechalet.annotations.Updated;

@Named
@SessionScoped
public class NoteHandler implements Serializable {

	private String note;

	private boolean editMode;

	@Inject
	private Event<ChangeNoteEvent> manager;

	// @Fires
	// Event<ChangeNoteEvent> event;

	public NoteHandler() {
		// TODO Auto-generated constructor stub
	}

	public String addNote1() {
		System.out.println("addNote1");
		setNote("");
		setEditMode(false);
		return "/test/test.xhtml";
	}

	public String addNote2() {
		System.out.println("addNote2");
		// event.fire(new ChangeNoteEvent(getNote()),
		// new AnnotationLiteral<Created>() {
		// });
		manager.fire(new ChangeNoteEvent(getNote()));
		return "/test/testOk.xhtml";
	}

	public String modNote1() {
		System.out.println("modNote1");
		setEditMode(true);
		return "/test/test.xhtml";
	}

	public String modNote2() {
		System.out.println("modNote2");
		setEditMode(false);
		manager.fire(new ChangeNoteEvent(getNote()));
		// event.fire(new ChangeNoteEvent(getNote()),
		// new AnnotationLiteral<Updated>() {
		// });
		return "/test/testOk.xhtml";
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		System.out.println("setNote: " + note);
		this.note = note;

	}

	public void afterCreated(@Observes @Created ChangeNoteEvent event) {
		System.out.println("Osservato - CREAZIONE: " + event.getNote());
	}

	public void afterUpdated(@Observes @Updated ChangeNoteEvent event) {
		System.out.println("Osservato - UPDATE: " + event.getNote());
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

}
