package network.tcp.mychat;

import java.util.UUID;

public class User {
    private String id;
    private String name;
    private boolean join;

    public User(String name) {
        this.id = UUID.randomUUID().toString().substring(0,8);
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

    public void setJoin(boolean join) {
        this.join = join;
    }
}
