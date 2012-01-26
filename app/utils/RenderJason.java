package utils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Date;

import models.User;
import play.exceptions.UnexpectedException;
import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.mvc.results.Result;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;

public class RenderJason extends Result {

    String json;

    public RenderJason(Object o, ExclusionStrategyJson exclusion) {
        Gson gson = new GsonBuilder()
            .setExclusionStrategies(exclusion)
            .registerTypeAdapter(Date.class, new DateDeserializer())
            .serializeNulls()
            .create();
        
        json = gson.toJson(o);
    }

    public RenderJason(Object o, Type type) {
        json = new Gson().toJson(o, type);
    }

    public RenderJason(Object o, JsonSerializer<?>... adapters) {
        GsonBuilder gson = new GsonBuilder();
        for (Object adapter : adapters) {
            Type t = getMethod(adapter.getClass(), "serialize").getParameterTypes()[0];
            gson.registerTypeAdapter(t, adapter);
        }
        json = gson.create().toJson(o);
    }

    public RenderJason(String jsonString) {
        json = jsonString;
    }

    public void apply(Request request, Response response) {
        try {
            String encoding = getEncoding();
            setContentTypeIfNotSet(response, "application/json; charset="+encoding);
            response.out.write(json.getBytes(encoding));
        } catch (Exception e) {
            throw new UnexpectedException(e);
        }
    }

    //
    static Method getMethod(Class<?> clazz, String name) {
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }
    
}
