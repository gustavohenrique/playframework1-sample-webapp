package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import play.Play;
import play.i18n.Lang;

public class ConverterUtil {

	public static Long toLong(String value) {
		try {
			return Long.valueOf(value);
		}
		catch (Exception e) {
			return 0l;
		}
	}
	
	public static Long toLong(Integer value) {
		try {
			return Long.valueOf(value);
		}
		catch (Exception e) {
			return 0l;
		}
	}
	
	public static Date toDate(String date) {
		try {
			SimpleDateFormat dateFormat = getDateFormatFromFileConfig();
			return dateFormat.parse(date);
		}
		catch (Exception e) { 
			return null;
		}
	}
	
	public static String fromDate(Date date) {
		try {
			SimpleDateFormat dateFormat = getDateFormatFromFileConfig();
			return dateFormat.format(date);
		}
		catch (Exception e) { 
			return null;
		}
	}

	private static SimpleDateFormat getDateFormatFromFileConfig() {
		String lang = Lang.get();
		String format = Play.configuration.getProperty("date.format."+lang);
		return new SimpleDateFormat(format);
	}
}
