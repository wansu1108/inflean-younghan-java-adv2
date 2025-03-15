package network.exception.close.rest;

import static util.MyLogger.log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

/*
 * 1. 서버가 close()를 호출하여, 클라이언트에게 FIN을 전송
 * 2. 클라이언트가 이를 무시하고, 서버에 PUSH를 전송
 * 3. 서버가 TCP규약에 의해, RST를 전송
 * => 서버에서 FIN을 전송하면, 클라이언트도 FIN을 전송해야 네트워크가 정상 종료된다.
*/
public class ResetCloseClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 45678);
        log("소캣 연결: " + socket);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // client <- server : FIN
        Thread.sleep(1000); // 서버가 close() 호출할 때 까지 잠시 대기

        // client -> server : PUSH[1]
        output.write(1);

        // client <- server : RST
        Thread.sleep(1000); // RST 메세지 전송 대기

        try {
            // java.net.SocketException: Connection reset
            int read = input.read();
            System.err.println("read = " + read);
        } catch (SocketException e) {
            log(e);
        }
        
        try {
            // java.net.SocketException: Broken pipe
            output.write(1);
        } catch (SocketException e) {
            log(e);
        }
    }
}
