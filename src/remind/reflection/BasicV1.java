package remind.reflection;

public class BasicV1 {

    public static void main(String[] args) throws ClassNotFoundException {
        // 클래스 메타데이터 조회 방법 3가지

        // 1. 클래스에서 찾기
        Class<User> basicClass1 = User.class;
        System.out.println("basicClass1= " + basicClass1);
        // 2. 인스턴스에서 찾기
        User user = new User();
        Class<? extends User> basicClass2 = user.getClass();
        System.out.println("basicClass2= " + basicClass2);
        // 3. 문자로 찾기
        Class<?> basicClass3 = Class.forName("remind.reflection.User");
        System.out.println("basicClass3= " + basicClass3);
    }
}
