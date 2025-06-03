package remind.chat;

import static remind.common.MyLogger.log;

import java.io.DataInputStream;
import java.io.EOFException;

public class ClientReadHandler implements Runnable {
    private DataInputStream input;
    private Client client;
    private boolean isClose;

    public ClientReadHandler(DataInputStream input, Client client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String recieved = input.readUTF();
                System.out.println(recieved);
            }
        } catch(EOFException e) {
            log("프로그램이 종료되었습니다.");
        } catch (Exception e) {
            log(e);
        } finally {
            client.close();
        }
    }

    public synchronized void close() {
        if(isClose) {
            return;
        }

        // 필요시 작성

        log("readHandler 종료");
        isClose = true;
    }
}
