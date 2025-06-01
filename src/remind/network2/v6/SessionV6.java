package remind.network2.v6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static remind.common.MyLogger.*;
import static remind.network2.SocketCloseUtil.closeAll;

public class SessionV6 implements Runnable {

    private final Socket socket;
    private final SessionManagerV6 sessionManager;
    private DataInputStream input;
    private DataOutputStream output;
    private boolean isClose = false;

    public SessionV6(Socket socket, SessionManagerV6 sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        sessionManager.add(this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String received = input.readUTF();
                System.out.println("received : " + received);
    
                if("exit".equals(received)) {
                    log("======== 서버 채팅프로그램 종료 ==========");
                    break;
                }
                
                String send = "hello " + received;
                output.writeUTF(send);
                System.out.println("send : " + send);       
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            sessionManager.remove(this);
            close();
        }
    }

    // 세션 종료시, 서버 종료시 동시에 호출 될 수 있다.
    public synchronized void close() {
        if(isClose) {
            return;
        }

        closeAll(socket, input, output);
        isClose = true;
        log("연결 종료: " + socket);
    }
}
