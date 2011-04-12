package by.giava.gestionechalet.controllers.event;

import java.io.Serializable;

public class ChangeNoteEvent implements Serializable {

	private String note;

	public ChangeNoteEvent() {

	}

	public ChangeNoteEvent(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
