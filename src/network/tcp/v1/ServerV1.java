package network.tcp.v1;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// TCP통신 : 서버
// ServerSocket을 통해 PORT를 점유
// 클라이언트로 부터 요청이 오면, Socket객체 생성, 내부 InputStream, OutputStream을 사용해 클라이언트와 통신
public class ServerV1 {

    private static final int PORT = 12345;
    
    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        Socket socket = serverSocket.accept();
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        log("소켓 연결: " + socket);

        String recevied = input.readUTF();
        log("Client -> Server: " + recevied);

        String toSend = recevied + " World";
        output.writeUTF(toSend);
        log("Client <- Server:" + toSend);
        
        // 자원정리
        log("연결 종료: " + socket);
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}
