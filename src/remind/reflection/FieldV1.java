package remind.reflection;

import java.lang.reflect.Field;

public class FieldV1 {
    public static void main(String[] args) {
        Class<User> user = User.class;

        System.out.println("---- getFields()");
        for(Field field : user.getFields()) {
            System.out.println("Field: " + field.getName());
        }

        System.out.println("---- getDeclaredFields()");
        for(Field field : user.getDeclaredFields()) {
            System.out.println("Field: " + field.getName());
        }
    }
}
