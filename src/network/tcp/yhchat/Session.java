package network.tcp.yhchat;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Session implements Runnable {
    
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;

    private final SessionManager sessionManager;
    private final CommandManager commnadManager;

    private String username;
    private boolean closed = false;

    public Session(Socket socket, SessionManager sessionManager, CommandManager commnadManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());

        this.sessionManager = sessionManager;
        this.commnadManager = commnadManager;
        this.sessionManager.add(this);
    }

    @Override
    public void run() {
        try {
            while(true) {
                String received = input.readUTF();
                System.out.println("client -> server: " + received);

                // 복잡하고 변경 가능성이 있는 코드는, 클래스로 치환하여, 유지보수와 가독성을 높인다.
                commnadManager.excute(received, this);
            }
        } catch (IOException e) {
            log(e); // IOException 발생 시, 개발자가 처리할 수 있는건 없다. log만 남긴다.
        } finally {
            sessionManager.sendAll(username + "님이 퇴장하였습니다.");
            sessionManager.remove(this);
            close();
        }
    }

    // Exception 발생 시, 종료시키기 위해, 예외를 외부로 던진다.
    public void send(String toSend) throws IOException {
        System.out.println("server -> client:" + toSend);
        output.writeUTF(toSend);
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public synchronized void close() {
        if(closed) {
            return ;
        }

        closeAll(socket, input, output);
        closed = true;
        log("");
    }
}
