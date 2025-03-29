package network.tcp.yhchat.command;

import java.io.IOException;

import network.tcp.yhchat.Session;
import network.tcp.yhchat.SessionManager;

public class ChangeCommand implements Command {
    
    private final SessionManager sessionManager;
    
    public ChangeCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void excute(String[] args, Session session) throws IOException {
        String changeName = args[1];
        sessionManager.sendAll(session.getUsername() + "님이 " + changeName + "로 이름을 변경했습니다.");
        session.setUserName(changeName);
    }
}
