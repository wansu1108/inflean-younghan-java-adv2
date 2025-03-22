package network.tcp.mychat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    private HandleRead handleRead;
    private HandleWrite handleWrite;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());

        this.handleRead = new HandleRead(input);
        this.handleWrite = new HandleWrite(output);
    }

    public void start() {
        Thread readThread = new Thread(handleRead);
        Thread writeThread = new Thread(handleWrite);
        readThread.start();
        writeThread.start();
    }
}
