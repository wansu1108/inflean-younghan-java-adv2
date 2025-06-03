package remind.chat.command;

import java.io.IOException;
import java.util.Arrays;

import remind.chat.Session;

public class DiscardCommand implements Command {

    @Override
    public void excute(String[] args, Session session) throws IOException {
        session.send("처리할 수 없는 명령어 입니다: " + Arrays.toString(args));
    }
}
