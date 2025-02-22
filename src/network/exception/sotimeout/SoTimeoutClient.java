package network.exception.sotimeout;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

// 서버가 요청에 대한 응답을 안해줄 때
public class SoTimeoutClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 45678);
        InputStream inputStream = socket.getInputStream();

        try {
            socket.setSoTimeout(3000);
            int read = inputStream.read();
            System.out.println("read : " + read);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }
    
        socket.close();
    }
}
