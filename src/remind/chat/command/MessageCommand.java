package remind.chat.command;

import static remind.common.MyLogger.log;

import remind.chat.Session;
import remind.chat.SessionManager;

public class MessageCommand implements Command {

    private final SessionManager sessionManager;

    public MessageCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void excute(String[] args, Session session) {
        log("[" + session.getName() + "]: " + args[1]);
        sessionManager.sendAll("[" + session.getName() + "]: " + args[1]);
    }
    
}
