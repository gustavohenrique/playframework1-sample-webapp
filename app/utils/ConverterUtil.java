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
			String lang = Lang.get();
			String dateFormat = Play.configuration.getProperty("date.format."+lang);
			SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
			return formatter.parse(date);
		}
		catch (Exception e) { 
			return null;
		}
	}
}
