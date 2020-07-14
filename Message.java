import java.io.Serializable;

// must implement Serializable in order to be sent
public class Message implements Serializable{
    private static int count = 0;
    private final int messageCount;
    private final String text;
    private final String type;

    public Message(String text) {
        this.text = text;
        this.type = "default";
        this.messageCount = ++count;
    }

    public String getText() {
        return text;
    }

    public int getMessageCount(){
        return messageCount;
    }

    public String getType(){
        return type;
    }
}

