package annotation.validator;

import annotation.validator.my.MyNotEmpty;
import annotation.validator.my.MyRange;

public class Team {
    @MyNotEmpty
    private String name;
    @MyRange(min = 1, max = 999)
    private int memberCount;

    public Team(String name, int memberCount) {
        this.name = name;
        this.memberCount = memberCount;
    }

    public String getName() {
        return name;
    }

    public int getMemberCount() {
        return memberCount;
    }
    
}
