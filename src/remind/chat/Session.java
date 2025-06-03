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
    private CommandManager commandManager;
    
    private boolean isClosed = false;
    private String name;

    public Session(Socket socket, SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.commandManager = new CommandManager(sessionManager);
        this.sessionManager.add(this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String originMessage = input.readUTF();
                commandManager.excute(originMessage, this);
            }         
        } catch (IOException e) {
            log(e);
        } finally {
            log(name + "님(이) 퇴장하였습니다.");
            sessionManager.remove(this);
            sessionManager.sendAll(name + "님(이) 퇴장하였습니다.");
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
