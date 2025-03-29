package network.tcp.yhchat.command;

import java.io.IOException;

import network.tcp.yhchat.Session;


public interface Command {
    public void excute(String[] args, Session session) throws IOException;
}
