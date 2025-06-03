package remind.chat;

import static remind.common.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private final int port;
    private final SessionManager sessionManager;
    private ServerSocket serverSocket;

    public Server(int port, SessionManager sessionManager) {
        this.port = port;
        this.sessionManager = sessionManager;
    }

    public void start() throws IOException {
        log("서버 시작");
        serverSocket = new ServerSocket(port);
        log("서버 소켓 시작 - 리스닝 포트: " + port);

        addShutdownHook();
        running();
    }

    private void addShutdownHook() {
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook));
    }

    private void running() throws IOException {
        while (true) {
            Session session = new Session(serverSocket.accept(), sessionManager);
            log("소켓 생성");
            Thread thread = new Thread(session);
            thread.start();
        }
    }

    public static class ShutdownHook implements Runnable {
        private final ServerSocket serverSocket;
        private final SessionManager sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManager sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("shutdownHook 실행");

            try{
                sessionManager.closeAll();
                serverSocket.close();

                Thread.sleep(1000);
            } catch (Exception e) {
                // 자원반환시 발생하는 오류는, 로그정도만 남긴다.
                e.printStackTrace();
                log(e);
            }
        }
    }
}
