package network.tcp.v6;

import java.util.ArrayList;
import java.util.List;

// 멀티스레드에서 사용할 매니저이다. 동시성을 고려해 synchronized 사용하였다.
public class SessionManagerV6 {

    private List<SessionV6> sessions = new ArrayList<>();

    public synchronized void add(SessionV6 session) {
         sessions.add(session);
    }

    public synchronized void remove(SessionV6 session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for(SessionV6 session : sessions) {
            session.close();
        }

        sessions.clear();
    }
}
