package remind.reflection;

import java.lang.reflect.Field;

public class FieldV2 {
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        User user = new User(1l, "Hong");
        Class<? extends User> clazz = user.getClass();
        
        System.out.println("변경 전 id: " + user.getId());
        for(Field field : clazz.getDeclaredFields()) {
            if(field.getName().equals("id")) {
                field.setAccessible(true);
                field.set(user, 2l);
                System.out.println("변경 후 id: " + user.getId());
            }
        }
    }
}
