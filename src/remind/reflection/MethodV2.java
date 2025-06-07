package remind.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodV2 {
    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        User user = new User();
        Class<? extends User> clazz = user.getClass();

        for(Method method : clazz.getDeclaredMethods()) {
            if(method.getName().equals("calcaulator")) {
                Object result = method.invoke(user, 1,2);
                System.out.println(result); // 3
            }
        }
    }   
}
