package by.training.interpol.command;

import by.training.interpol.entity.User;

import java.util.Optional;

public class UserAndResultMessageWrapper {
    private Optional<User> user;
    private String resultMessage;

    public UserAndResultMessageWrapper(Optional<User> user, String errorMessage) {
        this.user = user;
        this.resultMessage = errorMessage;
    }

    public Optional<User> getUser() {
        return user;
    }

    public void setUser(Optional<User> user) {
        this.user = user;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
