package remind.reflection;

import java.lang.reflect.Field;

public class FieldV3 {
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        User user = new User(null, null);
        System.out.println("===== before");
        System.out.println(user);
        nullFieldToDefault(user);
        System.out.println("===== after");
        System.out.println(user);

    }

    private static void nullFieldToDefault(Object target) throws IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = target.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(target) != null) {
                continue;
            }

            if (field.getType() == Long.class) {
                field.set(target, 0l);
            } else if (field.getType() == String.class) {
                field.set(target, "");
            }
        }
    }
}
