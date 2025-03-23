package network.tcp.yhchat;

import java.io.IOException;

public class CommnadManagerV1 implements CommandManager {

    private final SessionManager sessionManager;

    public CommnadManagerV1(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void excute(String totalMessage, Session session) throws IOException {

        if (totalMessage.startsWith("/exit")) {
            throw new IOException("exit");
        }

        sessionManager.sendAll(totalMessage);
    }
}
