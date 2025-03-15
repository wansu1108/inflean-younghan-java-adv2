package network.tcp.mychat;

public class User {
    private String id;
    private String name;
    private boolean join;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.join = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isJoin() {
        return join;
    }

    public void join() {
        this.join = true;
    }
}
