package reflection;

import java.lang.reflect.Field;

public class FieldV2 {
    public static void main(String[] args)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        User user = new User("id1", "userA", 20);
        System.out.println("기존 이름 = " + user.getName());

        Class<? extends User> userClass = User.class;
        Field nameField = userClass.getDeclaredField("name");

        // private 필드에 접근 허용, private 메서드도 이렇게 호출 가능 
        nameField.setAccessible(true);
        nameField.set(user, "userB");
        System.out.println("변경된 이름 = " + user.getName());
    }
}
