package network.tcp.mychat;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 서버소켓으로, 클라이언트 연결 요청까지 대기, tcp연결 되면 Session객체에 소켓을 전달한다.
public class Server {

    private static final int PORT = 12345;
    
    public static void main(String[] args) throws IOException {
        log("서버 시작");
        SessionManager sessionManager = new SessionManager();
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "shutdown"));

        // 위에서 셧다운 훅을 만나서 서버소켓이 종료되면, 블로킹 되어있는 곳에서, CloseException을 만나게 된다.
        try {
            while(true) {
                Socket socket = serverSocket.accept(); // 블로킹 => tcp연결이 될 때 까지

                Session session = new Session(socket,sessionManager);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (Exception e) {
            log("서버 소캣 종료: " + e);
        }
    }

    public static class ShutdownHook implements Runnable {

        private final ServerSocket serverSocket;
        private final SessionManager sessionManager;

        public ShutdownHook (ServerSocket serverSocket, SessionManager sessionManager) {
            this.serverSocket=serverSocket;
            this.sessionManager=sessionManager;
        }

        @Override
        public void run() {
        log("shutdownHook 실행");

            try {
                sessionManager.closeAll();
                serverSocket.close();

                Thread.sleep(1000);
            } catch (Exception e) { // 자원 정리 대기
                e.printStackTrace();
                System.out.println("e = " + e);
            }
        }
    }
}
