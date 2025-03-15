package network.tcp.mychat;

public class IllegalCommandException extends RuntimeException {

    public IllegalCommandException(String message) {
        super(message);
    }
}
