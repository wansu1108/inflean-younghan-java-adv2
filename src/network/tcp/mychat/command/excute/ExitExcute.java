package network.tcp.mychat.command.excute;

import static util.MyLogger.log;

import network.tcp.mychat.command.Excute;
import network.tcp.mychat.server.Session;
import network.tcp.mychat.server.SessionManager;

public class ExitExcute implements Excute {

    @Override
    public void excute(String message, Session self, SessionManager sessionManager) {

        String name = self.getUser().getName();
        boolean isJoin = self.getUser().isJoin();

        self.close(); // 종료

        try {
            if(isJoin) {
                for(Session session : sessionManager.findAll()) {
                    session.getOutput().writeUTF(name + "(이)가 퇴장했습니다.");
                }
            } else {
                self.getOutput().writeUTF("서버와의 접속이 종료되었습니다.");
            }
        } catch (Exception e) {
            log(e);
        }
    }
    
}
