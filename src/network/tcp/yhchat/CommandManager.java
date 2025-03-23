package network.tcp.yhchat;

import java.io.IOException;

public interface CommandManager {
    void excute(String message, Session session) throws IOException;
}
