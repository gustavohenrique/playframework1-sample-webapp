package controllers.api;

import play.mvc.Controller;
import utils.ExtJSReturn;

public class JsonController extends Controller {

	protected static void jsonOk(Object data, Long size) {
		if (size > 0) {
		    renderJSON(ExtJSReturn.mapOk(data, size));
		}
		renderJSON(ExtJSReturn.mapError("Object not found"));
	}
	
	protected static void jsonOk(String message) {
		renderJSON(ExtJSReturn.mapOk(message));
	}
	
	protected static void jsonError(String message) {
		renderJSON(ExtJSReturn.mapError(message));
	}
}
