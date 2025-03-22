package network.tcp.mychat.client;

import static util.MyLogger.log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class HandleWrite implements Runnable {

    private DataOutputStream output;

    public HandleWrite(DataOutputStream output) {
        this.output = output;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        
        try {
            while(true) {
                String toSend = scanner.nextLine();
                output.writeUTF(toSend);
            }   
        } catch (IOException e) {
            log(e);
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                log(e);
            }
        }
    }
}
