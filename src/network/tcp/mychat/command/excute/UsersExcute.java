package network.tcp.mychat.command.excute;

import static util.MyLogger.log;

import network.tcp.mychat.User;
import network.tcp.mychat.command.Excute;
import network.tcp.mychat.server.Session;
import network.tcp.mychat.server.SessionManager;

public class UsersExcute implements Excute {

    @Override
    public void excute(String message, Session self, SessionManager sessionManager) {

        try {
            self.getOutput().writeUTF("참가 중");
            self.getOutput().writeUTF("-----------------");
            for(Session session : sessionManager.findAll()) {
                User user = session.getUser();

                if(user.isJoin()) { // 참여중인 대상자
                    self.getOutput().writeUTF(user.getName());
                }
            }
            self.getOutput().writeUTF("-----------------");
        } catch (Exception e) {
            log(e);
        }
    }
}
