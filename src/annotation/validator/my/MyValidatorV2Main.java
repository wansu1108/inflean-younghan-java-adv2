package annotation.validator.my;

import static util.MyLogger.log;

import java.lang.reflect.Field;

import annotation.validator.Team;
import annotation.validator.User;

public class MyValidatorV2Main {
    public static void main(String[] args) {
        User user = new User("", 1);
        Team team = new Team("", 1);
        try {
            log("== user 검증 ==");
            validate(user);
        } catch (Exception e) {
            log(e);
        }
        try {
            log("== team 검증 ==");
            validate(team);
        } catch (Exception e) {
            log(e);
        }
    }

    private static void validate(Object target) throws IllegalArgumentException, IllegalAccessException {
        notEmpty(target);
        range(target);
    }

    private static void range(Object target) throws IllegalArgumentException, IllegalAccessException {

        Class<?> aClass = target.getClass();

        for(Field field : aClass.getDeclaredFields()) {
            MyRange range = field.getAnnotation(MyRange.class);
            if(range != null) {
                if(field.getType() == int.class) {
                    field.setAccessible(true);
                    int value = (int) field.get(target);
                    if(range.min() > value || range.max() < value) {
                        throw new RuntimeException(range.message());
                    }
                }
            }
        }
    }

    private static void notEmpty(Object target) throws IllegalArgumentException, IllegalAccessException {
        
        Class<?> aClass = target.getClass();

        for(Field field : aClass.getDeclaredFields()) {
            MyNotEmpty notEmpty = field.getAnnotation(MyNotEmpty.class);
            if(notEmpty != null) {
                if (field.getType() == String.class) {
                    field.setAccessible(true);
                    String value = (String) field.get(target);
                    if(value == null || value.isEmpty()) {
                        throw new RuntimeException(notEmpty.message());
                    }   
                }
            }
        }
    }
}
