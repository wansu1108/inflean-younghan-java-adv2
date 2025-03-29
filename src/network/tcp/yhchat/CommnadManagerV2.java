package network.tcp.yhchat;

import java.io.IOException;
import java.util.List;

public class CommnadManagerV2 implements CommandManager {

    private static final String DELIMETER = "\\|";
    private final SessionManager sessionManager;

    public CommnadManagerV2(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void excute(String totalMessage, Session session) throws IOException {
        if(totalMessage.startsWith("/join")) {
            String[] args = totalMessage.split(DELIMETER);
            String username = args[1];
            session.setUserName(username);
            sessionManager.sendAll("[" + username + "] 님이 입장하였습니다.");
        } else if(totalMessage.startsWith("/change")) {
            String[] args = totalMessage.split(DELIMETER);
            String changeName = args[1];
            sessionManager.sendAll(session.getUsername() + "님이 " + changeName + "로 이름을 변경했습니다.");
            session.setUserName(changeName);
        } else if (totalMessage.startsWith("/message")) {
            String[] args = totalMessage.split(DELIMETER);
            String message = args[1];
            sessionManager.sendAll("[" + session.getUsername() + "] " + message);
        } else if(totalMessage.startsWith("/users")) {
            List<String> users = sessionManager.getAllUsername();

            StringBuilder sb = new StringBuilder(); // StringBuffer(멀티스레드)와 StringBuilder(싱글스레드)의 차이점
            sb.append("전체 접속자 : ").append(users.size()).append("\n");
            for(String user : users) {
                sb.append(" - " + user).append("\n");
            }
            session.send(sb.toString());
        } else if (totalMessage.startsWith("/exit")) {
            throw new IOException("exit");
        } else {
            session.send("처리할 수 없는 명령어 입니다.: [" + totalMessage + "]");
        }
    }
}
