package remind.chat;

import static remind.common.MyLogger.log;

import java.io.DataOutputStream;
import java.util.Scanner;

public class ClientWritHandler implements Runnable {

    private DataOutputStream output;
    private Scanner scanner;
    private Client client;

    public ClientWritHandler(DataOutputStream output, Scanner scanner, Client client) {
        this.output = output;
        this.scanner = scanner;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String toSend = scanner.next();
                output.writeUTF(toSend);

                if(toSend.startsWith("/exit")) {
                    break;
                }
            }
        } catch (Exception e) {
            log(e);
        } finally {
            client.close();
        }
    }
}
