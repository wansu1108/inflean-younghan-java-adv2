package network.tcp.yhchat;

public class ServerMain {

    public final static int PORT = 12345;

    public static void main(String[] args) {

        SessionManager sessionManager = new SessionManager();
        // CommandManager commandManager = new CommnadManagerV1(sessionManager);
        CommandManager commandManager = new CommnadManagerV2(sessionManager);

        Server server = new Server(PORT, sessionManager, commandManager);
        server.start();
    }
}
