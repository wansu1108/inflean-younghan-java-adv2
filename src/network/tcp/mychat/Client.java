package network.tcp.mychat;

import static util.MyLogger.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

// read, write를 동시에 해결하는 문제 처리방법에 대해 고민하지 못했다.
// 멀티스레드를 활용하면 됐는데,,,
public class Client {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        
        // finally 블록에서 변수에 접근해야 한다. 따라서 try 블록 안에서 선언할 수 없다
        log("클라이언트 시작");
        try (
            Socket socket = new Socket("localhost", PORT);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input =  new DataInputStream(socket.getInputStream())){
            log("소켓 연결: " + socket);

            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.print("전송 문자: ");
                String toSend = scanner.nextLine();
                // 서버에게 문자 보내기
                output.writeUTF(toSend);

                if(toSend.equals("/exit")) {
                    break;
                }
                
                // 서버로부터 문자 받기
                String recived = input.readUTF();
                log(recived);
            }
            
            // 자원 정리
        } catch (Exception e) {
            log(e);
        }
    }
}
