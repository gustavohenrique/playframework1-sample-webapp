package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.parse(date);
		}
		catch (Exception e) { 
			return null;
		}
	}

    public static int toInteger(String value) {
        try {
            return Integer.valueOf(value);
        }
        catch (Exception e) {
            return 0;
        }
    }
}
