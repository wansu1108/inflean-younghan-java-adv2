package annotation.mapping;

import java.lang.reflect.Method;

public class TestControllerMain {
    public static void main(String[] args) {
        TestController testController = new TestController();
        Class<? extends TestController> aClass = testController.getClass();
        for(Method method : aClass.getDeclaredMethods()) {
            SimpleMapping annotation = method.getAnnotation(SimpleMapping.class);
            if(annotation != null) {
                String value = annotation.value();
                System.out.println("[" + value + "] --> TestController." + method.getName());
            }
        }
    }
}
