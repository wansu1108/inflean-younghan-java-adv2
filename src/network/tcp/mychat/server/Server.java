package network.tcp.mychat.server;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    public static void main(String[] args) {
        try (
            ServerSocket serverSocket = new ServerSocket(12345);   
        ) {
            SessionManager sessionManager = new SessionManager();
            
            ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
            Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook));

            while(true) {
                Socket socket = serverSocket.accept();
                Session session = new Session(socket, sessionManager);

                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            log(e);
        }
    }

    public static class ShutdownHook implements Runnable {

        private ServerSocket serverSocket;
        private SessionManager sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManager sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            try {
                System.out.println("서버의 연결이 종료되었습니다.");
                sessionManager.closeAll();
                serverSocket.close();

                Thread.sleep(1000); // 안정성을 고려하여
            } catch (Exception e) {
                log(e);
            }
        }
    }
}
