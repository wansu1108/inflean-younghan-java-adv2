package remind.chat.command;

import static remind.common.MyLogger.log;

import remind.chat.Session;
import remind.chat.SessionManager;

public class ChangeCommnad implements Command {

    private final SessionManager sessionManager;

    public ChangeCommnad(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void excute(String[] args, Session session) {
        log(session.getName() + "님(이) 닉네임을 변경하였습니다. " + session.getName() + " -> " + args[1]);
        sessionManager.sendAll(session.getName() + "님(이) 닉네임을 변경하였습니다. " + session.getName() + " -> " + args[1]);
        session.setName(args[1]);
    }
}
