package remind.network2.v6;

import java.io.IOException;
import java.net.ServerSocket;

import static remind.common.MyLogger.*;

public class SeverV6 {

    private static int PORT = 12345;

    public static void main(String[] args) throws IOException {
        SessionManagerV6 sessionManager = new SessionManagerV6();
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("======== 서버 채팅프로그램 실행 ==========");

        //Shutdown Hook 등록
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "shutdown"));

        try {
            while (true) {
                SessionV6 session = new SessionV6(serverSocket.accept(), sessionManager);
    
                Thread thread = new Thread(session);
                thread.start();
            }   
        } catch (Exception e) {
            log(e);
        }
    }
}
