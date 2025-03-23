package network.tcp.yhchat;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int port;
    private final SessionManager sessionManager;
    private final CommandManager commandManager;

    private ServerSocket serverSocket;

    public Server(int port, SessionManager sessionManager, CommandManager commandManager) {
        this.port = port;
        this.sessionManager = sessionManager;
        this.commandManager = commandManager;
    }

    public void start() {
        try {
            log("서버 시작: " + commandManager.getClass()); 
            serverSocket = new ServerSocket(port); 
            log("서버 소켓 시작 - 리스닝 포트: " + port);

            // 복잡한 코드는, 메서드로 치환하여 가독성을 높인다.
            addShutdownHook();
            running(); 
        } catch (IOException e) {
            log(e);
        } finally {
            sessionManager.closeAll();
        }
    }

    public void addShutdownHook() {
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "shutdownHook"));
    }

    public void running() throws IOException {
        while(true) {
            Socket socket = serverSocket.accept(); // 블로킹
            Session session = new Session(socket, sessionManager, commandManager);
            Thread thread = new Thread(session);
            thread.start();
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
