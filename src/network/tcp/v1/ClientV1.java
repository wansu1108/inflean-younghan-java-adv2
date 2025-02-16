package network.tcp.v1;

import static util.MyLogger.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// TCP 통신 : 클라이언트
// TCP 연결을 해주는 Socket객체, 내부 InputStream, OutputStream을 사용해 서버와 통신
public class ClientV1 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");
        Socket socket = new Socket("localhost", PORT);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input =  new DataInputStream(socket.getInputStream());
        log("소켓 연결: " + socket);

        // 서버에게 문자 보내기
        String toSend = "Hello";
        output.writeUTF(toSend);
        log("Client -> Servce: " + toSend);
        
        // 서버로부터 문자 받기
        String recived = input.readUTF();
        log("Client <- Servce: " + recived);

        // 자원 정리
        log("연결 종료: " + socket);
        output.close();
        input.close();
        socket.close();
    }
}
