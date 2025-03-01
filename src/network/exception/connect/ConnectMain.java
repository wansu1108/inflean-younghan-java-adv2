package network.exception.connect;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectMain {
    
    public static void main(String[] args) throws IOException {
        unknowEx1();
        unknowEx2();
        connectionRefused();
    }

    // 잘못된 IP
    private static void unknowEx1() throws IOException {
        try {
            Socket socket = new Socket("999.999.999.999",80);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    
    // DNS에 등록되지 않은 도메인
    private static void unknowEx2() throws IOException {
        try {
            Socket socket = new Socket("google.gogo",80);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 연결거절
     * 1. IP에 해당하는 서버에 접속은 성공하였지만, 연결이 거절되었다는 뜻이다.
     * - 사용중이지 않은 Port, 방화벽 등..
     * - 서버컴퓨터의 OS는 이때 TCP RST(reset) 패킷을 보내서 연결을 거절한다.
     * @throws IOException
     */
    private static void connectionRefused() throws IOException {
        Socket socket = new Socket("localhost",45678);
    }
}
