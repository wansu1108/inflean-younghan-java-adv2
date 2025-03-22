package network.tcp.mychat.command.excute;

import static util.MyLogger.log;

import network.tcp.mychat.User;
import network.tcp.mychat.command.Excute;
import network.tcp.mychat.server.Session;
import network.tcp.mychat.server.SessionManager;

public class JoinExcute implements Excute {

    @Override
    public void excute(String message, Session self, SessionManager sessionManager) {

        // 참여
        User selfUser = self.getUser();
        selfUser.setJoin(true);
        selfUser.setName(message);

        for(Session session : sessionManager.findAll()) {
            try {
                User user = session.getUser();

                if(user.isJoin()) { // 참여중인 대상에 대해서 메세지
                    session.getOutput().writeUTF(message + "(이)가 참여했습니다.");   
                }
            } catch (Exception e) {
                log(e);
            }
        }
    }
}
