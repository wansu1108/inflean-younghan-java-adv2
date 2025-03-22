package network.tcp.mychat.command.excute;

import static util.MyLogger.log;

import network.tcp.mychat.command.Excute;
import network.tcp.mychat.server.Session;
import network.tcp.mychat.server.SessionManager;

public class NoExcute implements Excute {

    @Override
    public void excute(String message, Session self, SessionManager sessionManager) {
        log("do No Excute");
    }
}
