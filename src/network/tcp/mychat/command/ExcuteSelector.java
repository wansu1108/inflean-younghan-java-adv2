package network.tcp.mychat.command;

import java.util.HashMap;
import java.util.Map;

import network.tcp.mychat.command.excute.ChangeExcute;
import network.tcp.mychat.command.excute.ExitExcute;
import network.tcp.mychat.command.excute.JoinExcute;
import network.tcp.mychat.command.excute.MessageExcute;
import network.tcp.mychat.command.excute.NoExcute;
import network.tcp.mychat.command.excute.UsersExcute;

public class ExcuteSelector {

    public ExcuteSelector() {
        excuteMap.put("/exit", new ExitExcute());
        excuteMap.put("/users", new UsersExcute());
        excuteMap.put("/change", new ChangeExcute());
        excuteMap.put("/message", new MessageExcute());
        excuteMap.put("/join", new JoinExcute());
    }

    private final Map<String, Excute> excuteMap = new HashMap<>();
    
    public Excute selectExctue(String command) {
        Excute excute = excuteMap.get(command);

        if(excute == null) {
            return new NoExcute();
        } else {
            return excute;
        }
    }

    public void addExcute(String command, Excute excute) {
        excuteMap.put(command, excute);
    }
}
