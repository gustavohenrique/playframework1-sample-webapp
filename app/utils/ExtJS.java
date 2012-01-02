package utils;

import java.util.HashMap;
import java.util.Map;

import play.mvc.Controller;
import play.mvc.results.RenderJson;

public class ExtJS {

	public static void success(Object data, Long size) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		if (size > 0) {
		    result.put("success", true);
	        result.put("data", data);
	        result.put("total", size);
        }
		else {
		    result.put("success", false);
		    result.put("message", "Object not found");
		}
		
		throw new RenderJson(result);
	}
	
	public static void success(String message) {
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("message", message);
		result.put("success", true);

		throw new RenderJson(result);
	} 
	
	public static void error(String message){
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("message", message);
		result.put("success", false);

		throw new RenderJson(result);
	}
}
