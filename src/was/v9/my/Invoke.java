package was.v9.my;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invoke {
    private Object instance;
    private Method method;

    public Invoke(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }

    public void invoke(Object[] args) {
        try {
            method.invoke(instance, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public Method getMethod() {
        return method;
    }
}
