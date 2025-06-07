package remind.annotation;

import java.lang.reflect.Field;

public class Validator {
    public static void valid(Object target) throws IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = target.getClass();

        for(Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(NotEmpty.class)) {
                Object value = field.get(target);
                NotEmpty annotation = field.getDeclaredAnnotation(NotEmpty.class);
                if(value == null || String.valueOf(value).isEmpty()) {
                    throw new IllegalArgumentException(annotation.message());
                }
            }

            if(field.isAnnotationPresent(Range.class)) {
                long value = field.getLong(target);
                Range annotation = field.getDeclaredAnnotation(Range.class);
                if(annotation.min() > value || value > annotation.max()) {
                    throw new IllegalArgumentException(annotation.message());
                }
            }
        }
    }
}
