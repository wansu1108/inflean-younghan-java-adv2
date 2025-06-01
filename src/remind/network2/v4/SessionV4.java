package remind.network2.v4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static remind.common.MyLogger.*;
import static remind.network2.SocketCloseUtil.closeAll;

public class SessionV4 implements Runnable {

    private final Socket socket;

    public SessionV4(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            
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
        } finally {
            // 자원정리
            closeAll(socket, dis, dos);
            log("연결 종료: " + socket);
        }
    }
}
