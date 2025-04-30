package annotation.java;

public class DeprecatedClass {
    
    public static void call1() {
        System.out.println("DeprecatedClass.call1");
    }

    @Deprecated
    public static void call2() {
        System.out.println("DeprecatedClass.call2");
    }

    @Deprecated(since = "2.4", forRemoval = true)
    public static void call3() {
        System.out.println("DeprecatedClass.call3");
    }

    public static void main(String[] args) {
        System.err.println("DeprecatedClass main");
        DeprecatedClass dc = new DeprecatedClass();
        dc.call1();
        dc.call2(); //IDE 경고
        dc.call3(); //IDE 경고(심각)
    }
}
