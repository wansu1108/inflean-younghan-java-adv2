package network.tcp.mychat.client;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.IOException;

public class HandleRead implements Runnable {

    private DataInputStream input;

    public HandleRead(DataInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String recieved = input.readUTF();
                System.out.println(recieved);
            }
        } catch (IOException e) {
            log(e);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                log(e);
            }
        }
    }
}
