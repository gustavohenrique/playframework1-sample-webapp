package utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import play.data.binding.TypeBinder;

public class GsonBinder implements TypeBinder<JsonObject> {

    public Object bind(String name, Annotation[] notes, String value, Class toClass, Type toType) throws Exception {
        return new JsonParser().parse(value);
    }

}
