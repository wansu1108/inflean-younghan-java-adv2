package remind.network1.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static remind.common.MyLogger.*;

public class ClientV2 {
    public static void main(String[] args) throws IOException {
        log("======== 클라이언트 채팅프로그램 실행 ==========");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("입력 : ");
            String send = scanner.nextLine();
            
            Socket socket = new Socket("localhost", 12345);

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(send);
            System.out.println("send : " + send);

            if("exit".equals(send)) {
                log("======== 클라이언트 채팅프로그램 종료 ==========");
                break;
            }

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String received = dis.readUTF();
            System.out.println("received : " + received);           
            
            // 자원정리
            log("연결 종료: " + socket);
            dis.close();
            dos.close();
            socket.close();
        }
    }
}
