package annotation.validator;

import annotation.validator.my.MyNotEmpty;
import annotation.validator.my.MyRange;

public class User {
    @MyNotEmpty
    private String name;
    @MyRange(min = 1, max = 999)
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
