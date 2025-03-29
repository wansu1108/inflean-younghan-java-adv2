package network.tcp.yhchat.command;

import java.io.IOException;

import network.tcp.yhchat.Session;
import network.tcp.yhchat.SessionManager;

public class MessageCommand implements Command {

    private final SessionManager sessionManager;
    
    public MessageCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void excute(String[] args, Session session) throws IOException {
            String message = args[1];
            sessionManager.sendAll("[" + session.getUsername() + "] " + message);
    }
}
