package network.tcp.yhchat;

import static util.MyLogger.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SessionManager {

    List<Session> sessions = new ArrayList<>();

    public synchronized void add(Session session) {
        sessions.add(session);
    }

    public synchronized void remove(Session session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for(Session session : sessions) {
            session.close();
        }
        sessions.clear();
    }

    public synchronized void sendAll(String message) {
        // 전체 메세지를 보내는 도중, 에러가 발생한다고, 해당 클라이언트의 연결을 종료시킬 순 없다. try_catch
        for(Session session : sessions) {
            try {
                session.send(message);
            } catch (IOException e) {
                log(e);
            }
        }
    }

    public synchronized List<String> getAllUsername() {
        List<String> users = new ArrayList<>();
        for(Session session : sessions) {
            if(session.getUsername() != null){
                users.add(session.getUsername());
            }
        }
        return users;
    }
}
