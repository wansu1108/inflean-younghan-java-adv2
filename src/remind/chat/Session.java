package remind.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static remind.common.MyLogger.log;
import static remind.network2.SocketCloseUtil.*;

public class Session implements Runnable {

    private final Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private final SessionManager sessionManager;
    private boolean isClosed = false;
    private String name;

    public Session(Socket socket, SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String originMessage = input.readUTF();
                Command command = new Command(originMessage);

                if ("join".equals(command.getCommnad())) {
                    this.name = command.getWords();
                    log(name + "님(이) 참가하셨습니다.");
                    sessionManager.sendAll("[" + name + "] 님(이) 참가하셨습니다.");
                } else if ("change".equals(command.getCommnad())) {
                    String newName = command.getWords();
                    log(newName + "님(이) 닉네임을 변경하였습니다. " + name + " -> " + newName);
                    sessionManager.sendAll(newName + "님(이) 닉네임을 변경하였습니다. " + name + " -> " + newName);
                    this.name = newName;
                } else if ("message".equals(command.getCommnad())) {
                    sessionManager.sendAll("[" + name + "]: " + command.getWords());
                    log("[" + name + "]: " + command.getWords());
                } else if ("users".equals(command.getCommnad())) {
                    send("============================");
                    int index = 1;
                    for(String userName : sessionManager.getAllUserName()) {
                        send(index + ". " + userName);
                        index++;
                    }
                    send("============================");
                } else if ("exit".equals(command.getCommnad())) {
                    break;
                } else {
                    log("알 수 없는 명령어 : " + command.getCommnad());
                    send("알 수 없는 명령어 : " + command.getCommnad());
                }   
            }         
        } catch (IOException e) {
            log(e);
        } finally {
            log(name + "님(이) 퇴장하였습니다.");
            sessionManager.remove(this);
            close();
        }
    }

    public void send(String message) {
        try {
            output.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized void close() {
        if(isClosed) {
            return;
        }

        closeAll(socket, input, output);
        isClosed = true;
    }
}
