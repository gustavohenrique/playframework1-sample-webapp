package controllers.api;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import models.Category;
import play.data.binding.Binder;
import play.mvc.With;
import utils.ConverterUtil;
import utils.ExtJS;
import utils.GsonBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import controllers.Secure;

@With(Secure.class)
public class Categories extends Users {
	
	static {
        Binder.register(JsonObject.class, new GsonBinder());
    }
	
	public static void read(Long id) {
		if (id != null) {
			Category category = Category.find("byUserAndId", Secure.user, id).first();
			ExtJS.success(category, 1l);
		}
		else {
			List<Category> categories = Category.find("user_id=? order by name", Secure.user.id).fetch();
			ExtJS.success(categories, ConverterUtil.toLong(categories.size()));
		}
	}
	
	public static void delete(Long id) {
		try {
			Category category = Category.find("byUserAndId", Secure.user, id).first();
			category.delete();
			ExtJS.success(category, 1l);
		}
		catch (Exception e) {
			ExtJS.error(e.getMessage());
		}
	}
	
	public static void create(JsonObject body) {
		Category submited = getSubmitedCategory(body);
		
		Category category = new Category();
		category.user = Secure.user;
		category.name = submited.name;
		
		validation.valid(category);
	    if(validation.hasErrors()) {
	    	ExtJS.error("Validation error: "+validation.errors().get(0).toString());
	    }
	    
	    try {
	    	category.save();
	    	ExtJS.success(category, 1l);
	    }
	    catch (Exception e) {
	    	ExtJS.error("Duplicate entrie.");
		}
	}
	
	public static void update(JsonObject body) {
		Category submited = getSubmitedCategory(body);
		
		Category category = Category.findById(submited.id);
		category.name = submited.name;
		
	    validation.valid(category);
	    if(validation.hasErrors()) {
	    	ExtJS.error("Validation error: "+validation.errors().get(0).toString());
	    }
	    
	    category.save();
		ExtJS.success(category, 1l);
	}
	
	private static Category getSubmitedCategory(JsonObject body) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.fromJson(body.get("data"), Category.class);
	}
	
}
