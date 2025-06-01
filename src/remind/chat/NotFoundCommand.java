package remind.chat;

public class NotFoundCommand extends RuntimeException {

    public NotFoundCommand(String message) {
        super(message);
    }

    public NotFoundCommand(String message, Throwable e) {
        super(message, e);
    }
}
