package was.my.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.*;

import static java.nio.charset.StandardCharsets.*;

public class MyHttpServerV2 {
    
    private final int port;

    public MyHttpServerV2(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        log("서버소켓 시작 PORT: " + port);
        ServerSocket serverSocket = new ServerSocket(port);
        
        while (true) {
            Socket socket = serverSocket.accept();
            MyRequestV2 requestV2 = new MyRequestV2(socket);
            Thread requestThread = new Thread(requestV2);
            requestThread.start();
        }
    }
}
