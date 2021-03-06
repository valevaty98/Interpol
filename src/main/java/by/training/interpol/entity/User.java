package by.training.interpol.entity;

import java.util.Objects;

public class User extends Entity {
    private String login;
    private String password;
    private String email;
    private Role role;
    private Assessment assessment;
    private Language lang;

    public User(long userId, String login, String password, String email, Role role, Assessment assessment, Language lang) {
        super(userId);
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.assessment = assessment;
        this.lang = lang;
    }

    public User(String login, String password, String email, Role role, Assessment assessment, Language lang) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.assessment = assessment;
        this.lang = lang;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", assessmentId=" + assessment.getId() +
                ", lang=" + lang +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return  getId() == user.getId() &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                role == user.role &&
                assessment == user.assessment &&
                lang == user.lang;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), login, password, email, role, assessment, lang);
    }
}

