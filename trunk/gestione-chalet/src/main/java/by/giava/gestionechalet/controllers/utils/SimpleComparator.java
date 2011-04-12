package by.giava.gestionechalet.controllers.utils;

import javax.faces.model.SelectItem;

public class SimpleComparator implements java.util.Comparator {

	public int compare(Object o1, Object o2) {
		String lastName1 = ((SelectItem) o1).getLabel().toUpperCase();
		String lastName2 = ((SelectItem) o2).getLabel().toUpperCase();
		return lastName1.compareTo(lastName2);
	}
}
