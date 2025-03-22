package network.tcp.mychat.command.excute;

import static util.MyLogger.log;

import network.tcp.mychat.User;
import network.tcp.mychat.command.Excute;
import network.tcp.mychat.server.Session;
import network.tcp.mychat.server.SessionManager;

public class MessageExcute implements Excute {
    
    @Override
    public void excute(String message, Session self, SessionManager sessionManager) {
        try {
            if(self.getUser().isJoin()) {
                for(Session session : sessionManager.findAll()) {
                    User user = session.getUser();
        
                    if(user.isJoin()) { // 참여중인 대상에 대해서 메세지
                        session.getOutput().writeUTF(message);   
                    }
                }
            } else {
                self.getOutput().writeUTF(message);
            }
        } catch (Exception e) {
            log(e);
        }
    }
    
}
