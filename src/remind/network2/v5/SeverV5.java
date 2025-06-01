package remind.network2.v5;

import java.io.IOException;
import java.net.ServerSocket;

import static remind.common.MyLogger.*;

public class SeverV5 {

    private static int PORT = 12345;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("======== 서버 채팅프로그램 실행 ==========");

        while (true) {
            SessionV5 session = new SessionV5(serverSocket.accept());
            Thread thread = new Thread(session);
            thread.start();
        }
    }   
}
