package by.training.interpol.entity;

public class FullMessageInfo {
    private Message message;
    private String userLogin;
    private String wantedPersonName;
    private String wantedPersonSurname;

    public FullMessageInfo(Message message, String userLogin, String wantedPersonName, String wantedPersonSurname) {
        this.message = message;
        this.userLogin = userLogin;
        this.wantedPersonName = wantedPersonName;
        this.wantedPersonSurname = wantedPersonSurname;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getWantedPersonName() {
        return wantedPersonName;
    }

    public void setWantedPersonName(String wantedPersonName) {
        this.wantedPersonName = wantedPersonName;
    }

    public String getWantedPersonSurname() {
        return wantedPersonSurname;
    }

    public void setWantedPersonSurname(String wantedPersonSurname) {
        this.wantedPersonSurname = wantedPersonSurname;
    }
}
