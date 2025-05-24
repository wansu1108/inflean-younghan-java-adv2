package remind.network1.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static remind.common.MyLogger.*;

public class ClientV1 {
    public static void main(String[] args) throws IOException {
        log("======== 클라이언트 채팅프로그램 실행 ==========");
        Socket socket = new Socket("localhost", 12345);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        Scanner scanner = new Scanner(System.in);
        System.out.print("입력 : ");
        String send = scanner.nextLine();

        dos.writeUTF(send);
        System.out.println("send : " + send);

        String received = dis.readUTF();
        System.out.println("received : " + received);

        // 자원정리
        log("연결 종료: " + socket);
        dis.close();
        dos.close();
        socket.close();
    }
}
