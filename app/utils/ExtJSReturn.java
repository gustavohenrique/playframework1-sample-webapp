package utils;

import java.util.HashMap;
import java.util.Map;

public class ExtJSReturn {

	public static Map<String, Object> mapOk(Object data, Long size) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("data", data);
		result.put("total", size);
		
		return result;
	}
	
	public static Map<String,Object> mapOk(String message) {
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("message", message);
		result.put("success", true);

		return result;
	} 
	
	public static Map<String,Object> mapError(String message){
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("message", message);
		result.put("success", false);

		return result;
	}
}
