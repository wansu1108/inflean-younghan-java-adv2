package remind.reflection;

import java.lang.reflect.Method;

public class MethodV1 {
    public static void main(String[] args) {
        Class<User> clazz = User.class;
        
        System.out.println("---- getMethods()");
        for(Method method : clazz.getMethods()) {
            System.out.println("Method: " + method.getName());
        }

        System.out.println("---- getDeclaredMethods()");
        for(Method method : clazz.getDeclaredMethods()) {
            System.out.println("Method: " + method.getName());
        }
    }   
}
