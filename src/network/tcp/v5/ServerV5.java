package network.tcp.v5;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 서버소켓으로, 클라이언트 연결 요청까지 대기, tcp연결 되면 Session객체에 소켓을 전달한다.
public class ServerV5 {

    private static final int PORT = 12345;
    
    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        while(true) {
            Socket socket = serverSocket.accept(); // 블로킹 => tcp연결이 될 때 까지

            SessionV5 session = new SessionV5(socket);
            Thread thread = new Thread(session);
            thread.start();
        }
    }
}
