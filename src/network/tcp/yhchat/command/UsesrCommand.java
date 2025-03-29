package network.tcp.yhchat.command;

import java.io.IOException;
import java.util.List;

import network.tcp.yhchat.Session;
import network.tcp.yhchat.SessionManager;

public class UsesrCommand implements Command {

    private final SessionManager sessionManager;
    
    public UsesrCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void excute(String[] args, Session session) throws IOException {
            List<String> users = sessionManager.getAllUsername();

            StringBuilder sb = new StringBuilder(); // StringBuffer(멀티스레드)와 StringBuilder(싱글스레드)의 차이점
            sb.append("전체 접속자 : ").append(users.size()).append("\n");
            for(String user : users) {
                sb.append(" - " + user).append("\n");
            }
            session.send(sb.toString());
    }
}
