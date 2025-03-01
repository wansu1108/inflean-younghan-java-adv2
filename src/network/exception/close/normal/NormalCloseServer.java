package network.exception.close.normal;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 소켓이 연결되면 1초 후, 소켓을 종료한다.
public class NormalCloseServer {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(45678);
        Socket socket = serverSocket.accept();
        log("소캣 연결: " + socket);
        
        Thread.sleep(1000);
        socket.close();
        log("소켓 종료");
    }   
}
