package network.tcp.mychat.command;

public class Command {
    private String command;
    private String excute;

    private Command(String command, String excute) {
        this.command = command;
        this.excute = excute;
    }

    public String getCommand() {
        return command;
    }

    public String getExcute() {
        return excute;
    }

    public static Command createCommand(String recevied) {
        
        if(recevied == null || recevied.length() == 0) {
            return null;
        }

        if(!(recevied.trim().startsWith("/"))) {
            return null;
        }

        if(recevied.indexOf("|") == -1) {
            return new Command(recevied.trim(), "");
        }

        String[] args = recevied.split("\\|");
        return new Command(args[0], args[1]);
    }

    @Override
    public String toString() {
        return String.format("{commnad: %s, excute: %s}", command, excute);
    }

}
