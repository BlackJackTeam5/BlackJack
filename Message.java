//package blackjackself;
import java.io.Serializable;

// must implement Serializable in order to be sent
public class Message implements Serializable  {
    public static final long serialVersionUID=5950169519310163575L;
    private static int count = 0;
    private final int messageCount;
    private final String text;
    private final String type;

    public Message(String type, String text) {
        this.text = text;
        this.type = "default";
        this.messageCount = ++count;
    }
    public Message()
    {
        messageCount = 0;
        text = "";
        type = "";
    }
    
    public String getInput() {
        return text;
    }
    
    public int getMessageCount(){
        return messageCount;
    }

    public String getType(){
        return type;
    }
}

