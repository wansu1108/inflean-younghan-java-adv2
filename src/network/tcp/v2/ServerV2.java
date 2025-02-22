package network.tcp.v2;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 심플 콘솔 채팅 프로그램
 *  1. exit문자 입력시 종료
 */
public class ServerV2 {

    private static final int PORT = 12345;
    
    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        Socket socket = serverSocket.accept();
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
        
        // 자원정리
        log("연결 종료: " + socket);
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}
