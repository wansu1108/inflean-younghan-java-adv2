package remind.chat;

public class Command {
    private static final String DELIMETER = "\\|";

    private String command;
    private String words;

    public Command(String message) {
        convert(message);
    }

    private void convert(String message) {
        if(message == null || message.isEmpty()) {
            return;
        }

        if(!message.trim().startsWith("/")) {
            this.command = "message";
            this.words = message;
            return;
        }

        String[] parts = message.trim().split(DELIMETER);
        this.command = parts[0].substring(1);
        this.words = parts.length > 1 ? parts[1] : "";
    }

    public String getCommnad() {
        return this.command;
    }

    public String getWords() {
        return this.words;
    }
}
