package network.tcp.mychat.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Client client = new Client(new Socket("localhost", 12345));
        client.start();
    }
}
