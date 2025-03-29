package network.tcp.yhchat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import network.tcp.yhchat.command.ChangeCommand;
import network.tcp.yhchat.command.Command;
import network.tcp.yhchat.command.ExitCommand;
import network.tcp.yhchat.command.JoinCommand;
import network.tcp.yhchat.command.MessageCommand;
import network.tcp.yhchat.command.UsesrCommand;

public class CommnadManagerV3 implements CommandManager {

    private static final String DELIMETER = "\\|";
    private final Map<String, Command> commands = new HashMap<>();

    public CommnadManagerV3(SessionManager sessionManager) {
        commands.put("/join", new JoinCommand(sessionManager));
        commands.put("/change", new ChangeCommand(sessionManager));
        commands.put("/message", new MessageCommand(sessionManager));
        commands.put("/users", new UsesrCommand(sessionManager));
        commands.put("/exit", new ExitCommand());
    }

    @Override
    public void excute(String totalMessage, Session session) throws IOException {
        String[] args = totalMessage.split(DELIMETER);
        
        Command command = commands.get(args[0]);

        if(command == null) { // null 처리
            session.send("처리할 수 없는 명령어 입니다.: [" + totalMessage + "]");
            return;
        }
        command.excute(args, session);
    }
}
