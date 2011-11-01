package utils;

import java.util.HashMap;
import java.util.Map;

public class ExtJSReturn {

	public static Map<String, Object> mapOk(Object data, Long size) {
		Map result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("data", data);
		result.put("total", size);
		
		return result;
	}
	
	public static Map<String,Object> mapError(String msg){
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("message", msg);
		result.put("success", false);

		return result;
	} 
}
