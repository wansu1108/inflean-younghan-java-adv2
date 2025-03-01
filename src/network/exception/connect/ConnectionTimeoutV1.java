package network.exception;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

// 타임아웃 설정 X
public class ConnectionTimeoutV1 {
    public static void main(String[] args) throws IOException {
        Long start = System.currentTimeMillis();
        
        try {
            new Socket("192.168.0.150", 45678);   
        } catch (ConnectException e) {
            e.printStackTrace();
        }

        Long end = System.currentTimeMillis();
        System.out.println("Time Taken : " + (end - start));
    }
}
