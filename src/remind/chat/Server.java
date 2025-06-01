package remind.chat;

import static remind.common.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class Server {
    
    private final int port;
    private final SessionManager sessionManager;

    public Server(int port, SessionManager sessionManager) {
        this.port = port;
        this.sessionManager = sessionManager;
    }

    public void start() throws IOException {

        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(port);
        log("서버 소켓 시작 - 리스닝 포트: " + port);

        while(true) {
            Session session = new Session(serverSocket.accept(), sessionManager);
            log("소켓 생성");
            Thread thread = new Thread(session);
            thread.start();
        }
    }
}
