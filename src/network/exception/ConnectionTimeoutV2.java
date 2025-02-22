package network.exception;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

// 타임아웃 설정 O
public class ConnectionTimeoutV2 {
    public static void main(String[] args) throws IOException {
        Long start = System.currentTimeMillis();
        
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("192.168.0.150", 45678), 3000);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }

        Long end = System.currentTimeMillis();
        System.out.println("Time Taken : " + (end - start));
    }
}
