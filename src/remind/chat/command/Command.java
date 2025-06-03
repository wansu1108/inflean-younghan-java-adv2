package remind.chat.command;

import java.io.IOException;

import remind.chat.Session;

public interface Command {
    public void excute(String[] args, Session session) throws IOException;
}
