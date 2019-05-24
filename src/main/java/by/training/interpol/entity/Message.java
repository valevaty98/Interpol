package by.training.interpol.entity;

public class Message extends Entity {
    private String subject;
    private String message;
    private String date;
    private Long wantedPersonId;
    private Long userId;
    private MessageStatus status;

    public Message(String messageText) {
        this.message = messageText;
    }

    public Message(String subject, String message, String date, Long wantedPersonId, Long userId, MessageStatus status) {
        this.subject = subject;
        this.message = message;
        this.date = date;
        this.wantedPersonId = wantedPersonId;
        this.userId = userId;
        this.status = status;
    }

    public Message(Long id, String subject, String message, String date, Long wantedPersonId, Long userId, MessageStatus status) {
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

    public Long getWantedPersonId() {
        return wantedPersonId;
    }

    public void setWantedPersonId(Long wantedPersonId) {
        this.wantedPersonId = wantedPersonId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
