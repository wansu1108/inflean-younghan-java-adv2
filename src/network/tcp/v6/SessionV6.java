package network.tcp.v6;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.*;

// 서버를 종료했을 때 자원정리 문제점을 해결한다.
// SessionManager를 통해, 서버소켓이 종료되었을 때, 모든 클라이언트의 소켓을 종료해주어야 한다.
public class SessionV6 implements Runnable{

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManagerV6 sessionManager;
    private boolean isClosed = false; // 플래그 사용이유는 한번 생각해보기~

    public SessionV6(Socket socket, SessionManagerV6 sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
    }

    @Override
    public void run() {
        try {
            log("소켓 연결: " + socket);
        
            while(true) {
                String recevied = input.readUTF(); // 대기(문자열, EOF)
                log("Client -> Server: " + recevied);

                if(recevied.equals("exit")) {
                    break;
                }

                String toSend = recevied + " World";
                output.writeUTF(toSend);
                log("Client <- Server:" + toSend);
            }
    
        } catch (IOException e) {
            log(e);
        } finally {
            sessionManager.remove(this);
            close(); // 자원정리
        }
    }

    // 세션 종료시, 서버소켓 종료시 중복 호출될 수 있다.
    public void close() {
        if(isClosed) { // 중복호출 방지
            return;
        }

        closeAll(socket, input, output);
        isClosed = true;
        log("연결 종료");
    }
}
