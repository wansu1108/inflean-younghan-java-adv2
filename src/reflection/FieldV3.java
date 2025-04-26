package reflection;

public class FieldV3 {
    public static void main(String[] args) {
        // 비지니스 로직 : null값 을 기본값으로 대체한다. String : "" , Integer : 0;
        User user = new User("id1", null, null);
        Team team = new Team("team1", null);
        System.out.println("===== before =====");
        System.out.println("user = " + user);
        System.out.println("team = " + team);
        if (user.getId() == null) {
            user.setId("");
        }
        if (user.getName() == null) {
            user.setName("");
        }
        if (user.getAge() == null) {
            user.setAge(0);
        }
        if (team.getId() == null) {
            team.setId("");
        }
        if (team.getName() == null) {
            team.setName("");
        }
        System.out.println("===== after =====");
        System.out.println("user = " + user);
        System.out.println("team = " + team);
    }
}
