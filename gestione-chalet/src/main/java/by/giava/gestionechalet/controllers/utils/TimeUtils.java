package by.giava.gestionechalet.controllers.utils;


import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	public static Long getDiffDays(Date dataInit, Date dataEnd) {
		System.out.println("DATE: " + dataInit + "-" + dataEnd);
		if (dataEnd.after(dataInit)) {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(dataInit);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(dataEnd);
			Long numDay = (new Long(cal2.getTimeInMillis()
					- cal1.getTimeInMillis()) / (new Long(24 * 60 * 60 * 1000))

			);
			return numDay;
		} else {
			return new Long(0);
		}
	}
}
