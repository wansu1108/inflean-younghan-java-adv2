package remind.annotation;

import static remind.common.MyLogger.log;
import static remind.annotation.Validator.*;

public class ValidatorMain {
    public static void main(String[] args) {
        User user = new User("홍길동", 999);
        try {
            valid(user);
        } catch (Exception e) {
            log(e.getMessage());
        }
    }
}
