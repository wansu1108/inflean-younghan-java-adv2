package network.tcp.v4;

import static util.MyLogger.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static network.tcp.SocketCloseUtil.*;

// TCP 통신 : 클라이언트
// exit문자 입력시 종료
public class ClientV4 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {

        // finally 블록에서 변수에 접근해야 한다. 따라서 try 블록 안에서 선언할 수 없다
        log("클라이언트 시작");
        Socket socket = null;
        DataOutputStream output = null;
        DataInputStream input =  null;
        
        try {
            socket = new Socket("localhost", PORT);
            output = new DataOutputStream(socket.getOutputStream());
            input =  new DataInputStream(socket.getInputStream());
            log("소켓 연결: " + socket);

            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.println("전송 문자: ");
                String toSend = scanner.nextLine();
                // 서버에게 문자 보내기
                output.writeUTF(toSend);
                log("Client -> Servce: " + toSend);

                if(toSend.equals("exit")) {
                    break;
                }
                
                // 서버로부터 문자 받기
                String recived = input.readUTF();
                log("Client <- Servce: " + recived);
            }
        } catch (Exception e) {
            log(e);
        } finally {
            // 자원 정리
            closeAll(socket, input, output);
            log("연결 종료: " + socket);
        }
    }
}
