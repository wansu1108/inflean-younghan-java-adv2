package network.exception.sotimeout;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 서버가 클라이언트 요청을 받고 무한대기
public class SoTimeoutServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(45678);
        Socket socket = serverSocket.accept();
    
        Thread.sleep(10000000);
    }
}
