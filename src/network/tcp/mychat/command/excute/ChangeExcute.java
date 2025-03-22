package network.tcp.mychat.command.excute;

import static util.MyLogger.log;

import network.tcp.mychat.User;
import network.tcp.mychat.command.Excute;
import network.tcp.mychat.server.Session;
import network.tcp.mychat.server.SessionManager;

public class ChangeExcute implements Excute {

    @Override
    public void excute(String message, Session self, SessionManager sessionManager) {

        String beforeName = self.getUser().getName();

        User user = self.getUser();
        user.setName(message);

        try {
            self.getOutput().writeUTF("이름이 변경되었습니다. " + beforeName + " -> " + message);   
        } catch (Exception e) {
            log(e);
        }
    }
}
