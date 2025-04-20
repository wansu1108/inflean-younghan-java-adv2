package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import reflection.data.BasicData;

public class MethodV2 {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 1. 클래스를 찾고
        BasicData basicDataInstance = new BasicData();
        basicDataInstance.call();
        // 2, 메서드를 찾고 , 메서드명으로 메서드를 호출하기
        Class<? extends BasicData> basicDataClass = basicDataInstance.getClass();
        String methodName = "hello";
        Method method = basicDataClass.getMethod(methodName, String.class);
        Object returnValue = method.invoke(basicDataInstance, "hi");
        System.out.println("returnValue = " + returnValue);
    }   
}
