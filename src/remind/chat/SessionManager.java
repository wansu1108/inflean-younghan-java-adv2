package remind.chat;

import static remind.common.MyLogger.log;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {

    private final List<Session> sessions = new ArrayList<>();

    public synchronized void add(Session session) {
        sessions.add(session);
    }

    public synchronized void remove(Session session) {
        sessions.remove(session);
    }

    public synchronized List<String> getAllUserName() {
        List<String> names = new ArrayList<>();
        for(Session session : sessions) {
            names.add(session.getName());
        }

        return names;
    }

    public synchronized void sendAll(String message) {
        for(Session session : sessions) {
            try {
                session.send(message);
            } catch (Exception e) {
                log(e);
            }
        }
    }
}
