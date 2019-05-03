package by.training.interpol.entity;

public class Message extends Entity {
    private String message;
    private User user;
    private String date;
    private long wantedPersonId;
    private long userId;

    public Message(String message, User user, String date, long wantedPersonId, long userId) {
        this.message = message;
        this.user = user;
        this.date = date;
        this.wantedPersonId = wantedPersonId;
        this.userId = userId;
    }

    public Message(long id, String message, User user, String date, long wantedPersonId, long userId) {
        super(id);
        this.message = message;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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