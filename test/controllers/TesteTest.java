package controllers;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.google.gson.Gson;

public class TesteTest extends TestCase {
	
	public void testAlgo() {
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("message", "messagedsdsds");
		result.put("success", true);
		String json = new Gson().toJson(result);
		assertEquals("{\"message\":\"messagedsdsds\",\"success\":true}", json);
	}

}
