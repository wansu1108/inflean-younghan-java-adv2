package network.tcp.mychat.command;

import network.tcp.mychat.server.Session;
import network.tcp.mychat.server.SessionManager;

public interface Excute {
    void excute(String message, Session self, SessionManager sessionManager);
}
