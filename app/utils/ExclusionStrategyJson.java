package utils;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ExclusionStrategyJson implements ExclusionStrategy {

    private Set<String> fields = new HashSet<String>();
    private Set<Class> classes = new HashSet<Class>();
    
    public void addField(String field) {
        fields.add(field);
    }
    
    public void addClass(Class clazz) {
        classes.add(clazz);
    }
    
    @Override
    public boolean shouldSkipClass(Class<?> c) {
        return false;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        // class has priority over field
        
        for (Class clazz : classes) {
            if (f.getDeclaredClass() == clazz)
                return true;
        }
        
        for (String field : fields) {
            if (f.getName().equals(field))
                return true;
        }
        return false;
    }

}
