package network.tcp.yhchat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import network.tcp.yhchat.command.ChangeCommand;
import network.tcp.yhchat.command.Command;
import network.tcp.yhchat.command.DefaultCommand;
import network.tcp.yhchat.command.ExitCommand;
import network.tcp.yhchat.command.JoinCommand;
import network.tcp.yhchat.command.MessageCommand;
import network.tcp.yhchat.command.UsesrCommand;

public class CommnadManagerV4 implements CommandManager {

    private static final String DELIMETER = "\\|";
    private final Map<String, Command> commands = new HashMap<>();
    private final DefaultCommand defaultCommand = new DefaultCommand();

    public CommnadManagerV4(SessionManager sessionManager) {
        commands.put("/join", new JoinCommand(sessionManager));
        commands.put("/change", new ChangeCommand(sessionManager));
        commands.put("/message", new MessageCommand(sessionManager));
        commands.put("/users", new UsesrCommand(sessionManager));
        commands.put("/exit", new ExitCommand());
    }

    @Override
    public void excute(String totalMessage, Session session) throws IOException {
        String[] args = totalMessage.split(DELIMETER);
        
        Command command = commands.getOrDefault(args[0], defaultCommand);
        command.excute(args, session);
    }
}
