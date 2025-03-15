package network.exception.close.rest;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ResetCloseServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        ServerSocket serverSocket = new ServerSocket(45678);
        Socket socket = serverSocket.accept();
        log("소캣 연결: " + socket);
        
        Thread.sleep(1000);
        socket.close();
        log("소켓 종료");
    }
}
