package by.training.interpol.entity;

public class Message extends Entity {
    private String message;
    private String date;
    private long wantedPersonId;
    private long userId;

    public Message(String message, String date, long wantedPersonId, long userId) {
        this.message = message;
        this.date = date;
        this.wantedPersonId = wantedPersonId;
        this.userId = userId;
    }

    public Message(long id, String message, String date, long wantedPersonId, long userId) {
        super(id);
        this.message = message;
        this.date = date;
        this.wantedPersonId = wantedPersonId;
        this.userId = userId;
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
}
