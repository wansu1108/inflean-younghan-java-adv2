package remind.network2.v6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import static remind.common.MyLogger.*;

public class ClientV6 {
    public static void main(String[] args) {
        log("======== 클라이언트 채팅프로그램 실행 ==========");
        try (
            Socket socket = new Socket("localhost", 12345);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        ) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("입력 : ");
                String send = scanner.nextLine();

                dos.writeUTF(send);
                System.out.println("send : " + send);

                if ("exit".equals(send)) {
                    log("======== 클라이언트 채팅프로그램 종료 ==========");
                    break;
                }

                String received = dis.readUTF();
                System.out.println("received : " + received);
            }    
        } catch (Exception e) {
            log(e);
        }
    }
}
