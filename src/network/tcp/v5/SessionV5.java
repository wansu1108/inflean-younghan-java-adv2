package network.tcp.v5;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// 멀티스레드 + 자원정리(try_with_resource)
// try with resource는 autocloseable 참고 -> 기존 자원 정리의 문제점을 모두 해결(6가지)
public class SessionV5 implements Runnable{

    private final Socket socket;

    public SessionV5(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream())){
            log("소켓 연결: " + socket);
        
            while(true) {
                String recevied = input.readUTF();
                log("Client -> Server: " + recevied);

                if(recevied.equals("exit")) {
                    break;
                }

                String toSend = recevied + " World";
                output.writeUTF(toSend);
                log("Client <- Server:" + toSend);
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        log("연결 종료");
    }
}
