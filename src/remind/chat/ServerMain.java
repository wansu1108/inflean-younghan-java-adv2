package remind.chat;

import java.io.IOException;

public class ServerMain {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        SessionManager sessionManager = new SessionManager();
        Server server = new Server(PORT, sessionManager);
        server.start();
    }
}
