package network.tcp.yhchat.command;

import java.io.IOException;

import network.tcp.yhchat.Session;
import network.tcp.yhchat.SessionManager;

public class JoinCommand implements Command {

    private final SessionManager sessionManager;
    
    public JoinCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void excute(String[] args, Session session) throws IOException {
        String username = args[1];
        session.setUserName(username);
        sessionManager.sendAll("[" + username + "] 님이 입장하였습니다.");
    }
}
