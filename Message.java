import java.time.LocalTime;

public class Message {
    private int senderID;
    private String content;
    private LocalTime timeStamp;

    public Message(int senderID, String content) {
        this.senderID = senderID;
        this.content = content;
        this.timeStamp = LocalTime.now();  // Timestamp when the message is created
    }

    public int getSenderID() {
        return senderID;
    }

    public LocalTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return timeStamp + " from #" + senderID + " (" + content + ")";
    }
}


