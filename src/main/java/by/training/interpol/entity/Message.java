package by.training.interpol.entity;

public class Message extends Entity {
    private String subject;
    private String message;
    private String date;
    private long wantedPersonId;
    private long userId;
    private MessageStatus status;

    public Message(String subject, String message, String date, long wantedPersonId, long userId, MessageStatus status) {
        this.subject = subject;
        this.message = message;
        this.date = date;
        this.wantedPersonId = wantedPersonId;
        this.userId = userId;
        this.status = status;
    }

    public Message(long id, String subject, String message, String date, long wantedPersonId, long userId, MessageStatus status) {
        super(id);
        this.subject = subject;
        this.message = message;
        this.date = date;
        this.wantedPersonId = wantedPersonId;
        this.userId = userId;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getWantedPersonId() {
        return wantedPersonId;
    }

    public void setWantedPersonId(long wantedPersonId) {
        this.wantedPersonId = wantedPersonId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}
