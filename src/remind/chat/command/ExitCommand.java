package remind.chat.command;

import java.io.IOException;

import remind.chat.Session;
import remind.chat.SessionManager;

public class ExitCommand implements Command {

    private final SessionManager sessionManager;

    public ExitCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void excute(String[] args, Session session) throws IOException {
        throw new IOException("프로그램이 종료됩니다.");
    }
}
