package network.tcp.v3;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SessionV3 implements Runnable{

    private final Socket socket;

    public SessionV3(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
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

        // 자원 정리 => 문제가 있는 코드 수정이 필요하다.
        log("연결 종료: " + socket);
        input.close();
        output.close();
        socket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
