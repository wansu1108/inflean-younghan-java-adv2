package remind.reflection;

public class User extends Parent{
    private Long id;
    private String name;

    public User() {}

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    private void discardUserMethod() {
        System.out.println("hello");
    }

    public int calcaulator(int a, int b) {
        return a + b;
    }

    @Override
    public String toString() {
        return String.format("{id: %d, name: '%s'}", id, name);
    }
}
