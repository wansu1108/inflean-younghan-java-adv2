import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        
    }

    public static class Member {
        private String id;
        private String name;
        private int age;

        public Member() {
        }

        public Member(String id, String name, Integer age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Member{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static interface MemberRepository {
        void add(Member member);
        List<Member> findAll();
    }

    public static class MemoryMemberRepository implements MemberRepository {

        private final List<Member> members = new ArrayList<>();

        @Override
        public void add(Test.Member member) {
            members.add(member);
        }

        @Override
        public List<Test.Member> findAll() {
            return members;
        }
    }
}