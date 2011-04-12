package by.giava.gestionechalet.controllers.utils;

import java.util.Locale;
import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;

public class CustomDateTimeConverter extends DateTimeConverter {

	public CustomDateTimeConverter() {
		super();
		TimeZone tz = TimeZone.getTimeZone("Europe/Rome");
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		setTimeZone(tz);
		setLocale(new Locale("it/IT"));
		// here you can set your custom date pattern for your project
		setPattern("d/M/yyyy");
		// System.out.println("USO IL CONVERTER CUSTOM");
	}
}
