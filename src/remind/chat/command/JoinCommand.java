package remind.chat.command;

import static remind.common.MyLogger.log;

import remind.chat.Session;
import remind.chat.SessionManager;

public class JoinCommand implements Command {

    private final SessionManager sessionManager;

    public JoinCommand(SessionManager sessionManager2) {
        this.sessionManager = sessionManager2;
    }

    @Override
    public void excute(String[] args, Session session) {
        session.setName(args[1]);
        log(args[1] + "님(이) 참가하였습니다.");
        sessionManager.sendAll(args[1] + "님(이) 참가하였습니다.");
    }
}
