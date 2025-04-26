package reflection;

import java.lang.reflect.Field;

public class FieldUtil {
    public static void nullFieldToDefault(Object target) throws IllegalAccessException {
        Class<?> aClass = target.getClass();
        
        Field[] declearFields = aClass.getDeclaredFields();
        for(Field field : declearFields) {
            field.setAccessible(true);
            if(field.get(target) != null) {
                continue;
            }

            if(field.getType() == String.class) {
                field.set(target, "");
            }

            if(field.getType() == Integer.class) {
                field.set(target, 0);
            }
        }
    }
}
