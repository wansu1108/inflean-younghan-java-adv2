package network.tcp.mychat;

public class Command {
    private String command;
    private String message;

    public Command(String command, String message) {
        this.command = command;
        this.message = message;
    }

    public static Command textToCommand(String text) {

        if(text == null || !text.trim().startsWith("/")) {
            throw new IllegalCommandException(String.format("잘못된 명령어 입니다: %s", text));
        }

        String[] words = text
            .trim()
            .substring(1)
            .split("\\|");

            if(words.length == 1) {
                return new Command(words[0], "");
            }

        return new Command(words[0], words[1]);
    }

    public String getCommnad() {
        return command;
    }

    public String getMessage() {
        return message;
    }
}
