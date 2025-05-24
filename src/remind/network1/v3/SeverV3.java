package remind.network1.v3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static remind.common.MyLogger.*;

public class SeverV3 {

    private static int PORT = 12345;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("======== 서버 채팅프로그램 실행 ==========");

        while (true) {
            SessionV3 session = new SessionV3(serverSocket.accept());
            Thread thread = new Thread(session);
            thread.start();
        }
    }   
}
