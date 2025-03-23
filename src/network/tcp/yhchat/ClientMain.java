package network.tcp.yhchat;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        Client client1 = new Client("localhost", 12345);
        client1.start();
    }
}
