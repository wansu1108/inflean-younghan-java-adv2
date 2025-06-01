package remind.network2.v5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static remind.common.MyLogger.*;

public class SessionV5 implements Runnable {

    private final Socket socket;

    public SessionV5(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            socket;
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        ){
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 자원이 정상 종료되는지 확인만 한다.
        log("연결 종료: " + socket + " isClosed: " + socket.isClosed());
    }
}
