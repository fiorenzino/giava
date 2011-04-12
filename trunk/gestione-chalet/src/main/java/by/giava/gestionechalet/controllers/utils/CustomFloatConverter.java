package by.giava.gestionechalet.controllers.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class CustomFloatConverter implements Converter {

	public CustomFloatConverter() {

	}

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("getAsObject INIT: " + arg2);
		return new Float(arg2);
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Float arg2F = (Float) arg2;
		System.out.println("getAsString INIT: " + arg2F);

		if (arg2F != null) {
			if (arg2F == 0)
				return "0,00";
			// System.out.println("getAsString-INIT: " + arg2);
			// NumberFormat nf3 = new DecimalFormat("#,###.00");
			// nf3.getNumberInstance(Locale.ITALIAN);
			// nf3.setGroupingUsed(true);
			// String result = nf3.format(arg2F);
			// System.out.println("getAsString-CONVERT: " + result);
			NumberFormat nf = new DecimalFormat("#,###.00");
			nf.setGroupingUsed(true);
			nf.getNumberInstance(Locale.ITALIAN);
			nf.setGroupingUsed(true);
			String result = nf.format(arg2F);
			result = result.replace(".", "_");
			result = result.replace(",", ".");
			result = result.replace("_", ",");
			System.out.println("getAsString RESULT: " + result);
			if (result.startsWith(","))
				result = "0" + result;
			return result;
		}
		return null;

	}
}
