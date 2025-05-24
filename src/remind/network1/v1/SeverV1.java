package remind.network1.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static remind.common.MyLogger.*;

public class SeverV1 {

    private static int PORT = 12345;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("======== 서버 채팅프로그램 실행 ==========");
        Socket socket = serverSocket.accept();
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        
        String received = dis.readUTF();
        System.out.println("received : " + received);
        
        String send = "hello " + received;
        dos.writeUTF(send);
        System.out.println("send : " + send);

        // 자원정리
        log("연결 종료: " + socket);
        dis.close();
        dos.close();
        socket.close();
    }   
}
