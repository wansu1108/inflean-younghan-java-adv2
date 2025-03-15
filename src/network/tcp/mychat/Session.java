package network.tcp.mychat;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

import static network.tcp.SocketCloseUtil.*;

// 1. if문으로 너무 복잡한 코드가 되었다.
// 2. java.util.ConcurrentModificationException, 전체 사용자에게 메시지를 보내는 코드에서 예외를 처리하지 못했다.
public class Session implements Runnable{

    private static final String DEFULAT_NAME = "Anonymous";

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManager sessionManager;
    private final User user;
    private boolean isClosed = false; // 플래그 사용이유는 한번 생각해보기~

    public Session(Socket socket, SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.user = new User(UUID.randomUUID().toString().substring(0, 8), DEFULAT_NAME);
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
    }

    @Override
    public void run() {
        try {
            log("소켓 연결: " + socket);
        
            while(true) {
                String recevied = input.readUTF(); // 대기(문자열, EOF)
                String toSend = user.getName() + ": " + recevied;
                log(toSend);
                // 대화방에 참가하지 않았다면, 채팅은 되돌아온다. 
                // 대화방에 참가하지 않았다면, join외 다른 커맨드는 동작하지 않는다.

                try {
                    Command command = Command.textToCommand(recevied);
                
                    if(!user.isJoin()) {
                        if(command.getCommnad().equals("join")) {
                            user.join(); // 참가
                            user.setName(command.getMessage());
                            output.writeUTF(command.getMessage() + "이(가) 참가하였습니다.");
                            log(command.getMessage() + "이(가) 참가하였습니다.");
                        } else {
                            output.writeUTF(toSend);
                        }
                    } else {
                        if(command.getCommnad().equals("join")) {
                            output.writeUTF("이미 대화방에 참가중입니다.");
                        } else if(command.getCommnad().equals("exit")) {
                            log(user.getName() + "이(가) 퇴장하였습니다.");
                            break;
                        } else if (command.getCommnad().equals("message")) {
                            String message = user.getName() + ":" + command.getMessage();

                            for(Session session : sessionManager.findAllSession()) {
                                session.toSend(message);
                            }

                            log(message);
                        } else if (command.getCommnad().equals("change")) {
                            output.writeUTF("닉네임을 변경하였습니다." + user.getName() + "->" + command.getMessage());
                            user.setName(command.getMessage());
                        } else if (command.getCommnad().equals("users")) {
                            StringBuffer sb = new StringBuffer();
                            sb.append("\r\n");
                            sb.append("------user--------\r\n");
                            for(Session session : sessionManager.findAllSession()) {
                                sb.append(session.getUser().getName() + "\r\n");
                            }
                            sb.append("------------------\r\n");
                            output.writeUTF(sb.toString());
                            log(sb.toString());
                        } else {
                            throw new IllegalCommandException(String.format("잘못된 명령어 입니다: %s", command.getCommnad()));
                        }
                    }
                } catch (IllegalCommandException e) {
                    output.writeUTF(e.getMessage());
                }
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

    public User getUser() {
        return user;
    }

    public void toSend(String message) {
        try {
            output.writeUTF(message);   
        } catch (IOException e) {
            log(e);
        } finally {
            sessionManager.remove(this);
            closeAll(socket, input, output);
        }
    }
}
