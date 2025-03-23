package network.tcp.yhchat;

import static util.MyLogger.log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class WriteHandler implements Runnable {

    private static final String DELIMITER = "|";

    private final DataOutputStream output;
    private final Client client;
    private boolean closed = false;

    public WriteHandler(DataOutputStream output, Client client) {
        this.output = output;
        this.client = client;
    }

    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);

        try {
            String username = inputUsername(scanner);
            output.writeUTF("/join" + DELIMITER + username);

            System.out.println("채팅방에 입장하였습니다.");
            while (true) {
                String toSend = scanner.nextLine();

                if(toSend.isEmpty()) {
                    continue;
                }

                if(toSend.equals("/exit")) {
                    output.writeUTF(toSend);
                    break;
                }

                if(toSend.startsWith("/")) {
                    output.writeUTF(toSend);
                } else {
                    output.writeUTF("/message" + DELIMITER + toSend);   
                }
            }
        } catch (IOException e){
            log(e);
        } finally {
            client.close();
        }
    }

    private String inputUsername(Scanner scanner) {
        System.out.print("이름을 입력하세요: ");
        String name;
        do {
            name = scanner.nextLine();
        } while (name.isEmpty());
        return name;
    }

    public synchronized void close() {
        if(closed) {
            return ;
        }
        try {
            System.in.close();
        } catch (IOException e) {
            log(e);
        }
        closed = true;
        log("writeHandler 종료");
    }
    
}
