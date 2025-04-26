package reflection;

import java.lang.reflect.Field;

public class FieldV1 {
    public static void main(String[] args) {
        Class<User> userClass = User.class;
        
        System.out.println("=========fields()=========");
        Field[] fields = userClass.getFields();
        for(Field field : fields) {
            System.out.println("field  = " + field);
        }

        System.out.println("=========declaredFields()=========");
        Field[] declaredFields = userClass.getDeclaredFields();
        for(Field field : declaredFields) {
            System.out.println("declaredField  = " + field);
        }
    }
}
