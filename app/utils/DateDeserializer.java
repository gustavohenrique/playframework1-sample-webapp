package utils;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;

public class DateDeserializer implements JsonDeserializer<Date> {
	private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public JsonElement serialize(Date src, Type arg1, JsonSerializationContext arg2) {
		String dateFormatAsString = format.format(new Date(src.getTime()));
		return new JsonPrimitive(dateFormatAsString);
	}

	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		if (! (json instanceof JsonPrimitive) ) {
			throw new JsonParseException("The date should be a string value");
		}
		try {
			Date date = format.parse(json.getAsString());
			return date;
		} catch (Exception e) {
			throw new JsonParseException(e);
		}
	}
}