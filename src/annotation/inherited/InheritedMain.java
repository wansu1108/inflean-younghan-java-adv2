package annotation.inherited;

import java.lang.annotation.Annotation;

public class InheritedMain {
    public static void main(String[] args) {
        print(Parent.class);
        print(Child.class);
        print(TestInterface.class);
        print(TestInterfaceImpl.class);
    }

    private static void print(Class<?> clazz) {
        System.out.println("class : " + clazz);
        for(Annotation anno : clazz.getAnnotations()) {
            System.out.println(" - " + anno.annotationType().getSimpleName());
        }
        System.out.println();
    }
}
