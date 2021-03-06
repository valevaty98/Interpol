package by.training.interpol.entity;

public class FullMessageInfo {
    private Message message;
    private String userLogin;
    private String userEmail;
    private String wantedPersonName;
    private String wantedPersonSurname;
    private String wantedPersonImage;

    public FullMessageInfo(Message message, String userLogin, String userEmail, String wantedPersonName, String wantedPersonSurname, String wantedPersonImage) {
        this.message = message;
        this.userLogin = userLogin;
        this.userEmail = userEmail;
        this.wantedPersonName = wantedPersonName;
        this.wantedPersonSurname = wantedPersonSurname;
        this.wantedPersonImage = wantedPersonImage;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public String getWantedPersonImage() {
        return wantedPersonImage;
    }

    public void setWantedPersonImage(String wantedPersonImage) {
        this.wantedPersonImage = wantedPersonImage;
    }
}
