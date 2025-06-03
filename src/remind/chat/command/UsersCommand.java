package remind.chat.command;

import remind.chat.Session;
import remind.chat.SessionManager;

public class UsersCommand implements Command {

    private final SessionManager sessionManager;

    public UsersCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void excute(String[] args, Session session) {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------------").append("\r\n");
        for(String name : sessionManager.getAllUserName()) {
            sb.append(" " + name).append("\r\n");
        }
        sb.append("-----------------------------");
        session.send(sb.toString());
    }
}
