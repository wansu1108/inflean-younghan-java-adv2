package remind.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConstructorV1 {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<User> clazz = User.class;

        Constructor<User> constructor = clazz.getDeclaredConstructor(long.class, String.class);
        // constructor.setAccessible(true); // private 생성자의 경우
        Object instance = constructor.newInstance(1l, "kim");
        System.out.println("instance: " + instance);
        
        Method id_method = clazz.getDeclaredMethod("getId");
        Method name_method = clazz.getDeclaredMethod("getName");
        Object id = id_method.invoke(instance);
        Object name = name_method.invoke(instance);
        System.out.println(id);
        System.out.println(name);
    }
}
