package remind.chat;

import static remind.common.MyLogger.log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ClientWriteHandler implements Runnable {

    private DataOutputStream output;
    private Scanner scanner;
    private Client client;

    private boolean isClose = false;

    public ClientWriteHandler(DataOutputStream output, Scanner scanner, Client client) {
        this.output = output;
        this.scanner = scanner;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String toSend = scanner.next();

                if(toSend.isEmpty()) {
                    continue;
                }
                
                if(toSend.equals("/exit")) {
                    output.writeUTF(toSend);
                    break;
                }
                
                output.writeUTF(toSend);
            }
        } catch (Exception e) {
            log(e);
        } finally {
            client.close();
        }
    }

    // synchronized의 이해와, close flag의 이해가 필요
    public synchronized void close() {
        if(isClose) {
            return;
        }

        try {
            System.in.close();
        } catch (IOException e) {
            log(e);
        }

        log("WriteHandler 종료");
        isClose = true;
    }
}
