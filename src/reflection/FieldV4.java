package reflection;

public class FieldV4 {
    public static void main(String[] args) throws IllegalAccessException {
        // 비지니스 로직 : null값 을 기본값으로 대체한다. String : "" , Integer : 0;
        User user = new User("id1", null, null);
        Team team = new Team("team1", null);
        System.out.println("===== before =====");
        System.out.println("user = " + user);
        System.out.println("team = " + team);

        FieldUtil.nullFieldToDefault(user);
        FieldUtil.nullFieldToDefault(team);
        System.out.println("===== after =====");
        System.out.println("user = " + user);
        System.out.println("team = " + team);
    }
}
