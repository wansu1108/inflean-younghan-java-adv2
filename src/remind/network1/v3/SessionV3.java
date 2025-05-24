package remind.network1.v3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static remind.common.MyLogger.*;

public class SessionV3 implements Runnable {

    private final Socket socket;

    public SessionV3(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
            while (true) {
                String received = dis.readUTF();
                System.out.println("received : " + received);
    
                if("exit".equals(received)) {
                    log("======== 서버 채팅프로그램 종료 ==========");
                    break;
                }
                
                String send = "hello " + received;
                dos.writeUTF(send);
                System.out.println("send : " + send);       
            }

            // 자원정리
            log("연결 종료: " + socket);
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
