package remind.chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import remind.chat.command.ChangeCommnad;
import remind.chat.command.Command;
import remind.chat.command.DiscardCommand;
import remind.chat.command.ExitCommand;
import remind.chat.command.JoinCommand;
import remind.chat.command.MessageCommand;
import remind.chat.command.UsersCommand;

public class CommandManager {

    private final String DELIMETER = "\\|";

    private static final Map<String, Command> commandMap = new HashMap<>();

    public CommandManager(SessionManager sessionManager) {
        commandMap.put("/join", new JoinCommand(sessionManager));
        commandMap.put("/change", new ChangeCommnad(sessionManager));
        commandMap.put("/message", new MessageCommand(sessionManager));
        commandMap.put("/users", new UsersCommand(sessionManager));
        commandMap.put("/exit", new ExitCommand(sessionManager));
    }

    public void excute(String message, Session session) throws IOException {
        if(message == null || message.isEmpty()) {
            return;
        }

        String[] parts = convertMessage(message);

        Command command = commandMap.getOrDefault(parts[0], new DiscardCommand());
        command.excute(parts, session);
    }

    private String[] convertMessage(String message) {
        if(message.startsWith("/")) {
            return message.split(DELIMETER);
        } else {
            String[] parts = new String[2];
            parts[0] = "/message";
            parts[1] = message;

            return parts;
        }
    }
}
