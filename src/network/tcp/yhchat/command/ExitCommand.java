package network.tcp.yhchat.command;

import java.io.IOException;

import network.tcp.yhchat.Session;

public class ExitCommand implements Command {

    @Override
    public void excute(String[] args, Session session) throws IOException {
        throw new IOException("exit");
    }
}
