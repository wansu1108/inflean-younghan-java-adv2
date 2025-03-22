package network.tcp.mychat.server;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import network.tcp.mychat.User;
import network.tcp.mychat.command.Command;
import network.tcp.mychat.command.Excute;
import network.tcp.mychat.command.ExcuteSelector;

public class Session implements Runnable {

    private final Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private SessionManager sessionManager;
    private boolean closed = false;
    private User user;

    public Session(Socket socket, SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
        this.user = new User("Anonymous"); // 익명으로 유저 생성
    }

    @Override
    public void run() {
        ExcuteSelector excuteSelector = new ExcuteSelector();
        try {
            while(true) {
                String received = input.readUTF();
                Command command = Command.createCommand(received);

                if(command != null) {
                    Excute excute = excuteSelector.selectExctue(command.getCommand());
                    excute.excute(command.getExcute(), this, sessionManager);
                } else {
                    output.writeUTF("잘못된 명령어: " + received);
                }
            }       
        } catch (IOException e) {
            log(e);
        } finally {
            log("클라이언트와의 연결이 끊어졌습니다.");
            sessionManager.remove(this);
            closeAll(socket, input, output);
        }
    }
    
    public DataOutputStream getOutput () {
        return output;
    }

    public User getUser () {
        return user;
    }

    public void close() {
        if(closed) { // 중복 호출 방지 & 멀티스레드 고려
            return;
        } 

        log("연결 종료");
        closeAll(socket, input, output);
        closed = true;
    }
}
