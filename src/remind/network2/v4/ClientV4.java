package remind.network2.v4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static remind.network2.SocketCloseUtil.*;

import static remind.common.MyLogger.*;

public class ClientV4 {
    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;

        try {
            log("======== 클라이언트 채팅프로그램 실행 ==========");
            socket = new Socket("localhost", 12345);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());

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
        } catch (IOException e) {
            log(e);
        } finally {
            closeAll(socket, dis, dos);
            log("연결 종료: " + socket);
        }
    }
}
