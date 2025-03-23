package network.tcp.yhchat;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.IOException;

public class ReadHandler implements Runnable {

    private final DataInputStream input;
    private final Client client; // 자원정리는 client객체
    private boolean closed = false;

    public ReadHandler(DataInputStream input, Client client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            String received = input.readUTF();
            System.out.println(received);
        } catch (IOException e) {
            log(e);
        } finally {
            client.close();
        }
    }

    public synchronized void close() {
        if(closed) {
            return;
        }
        // 필요시 종료 로직 작성
        closed = true;
        log("readHandler 종료");
    }
}
