package network.tcp.v4;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.*;

// 멀티스레드 + 자원정리
public class SessionV4 implements Runnable{

    private final Socket socket;

    public SessionV4(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // finally 블록에서 변수에 접근해야 한다. 따라서 try 블록 안에서 선언할 수 없다
        DataOutputStream output = null;
        DataInputStream input = null;

        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
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
        } finally {
            closeAll(socket, input, output);
            log("연결 종료: " + socket);
        }
    }
}
