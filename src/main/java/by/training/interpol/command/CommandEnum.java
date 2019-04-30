package by.training.interpol.command;

public enum CommandEnum {
    LOGIN (new LoginCommand()),
    SIGNUP (new SignUpCommand()),
    HOME (new HomeCommand()),
    LOGOUT (new LogoutCommand()),
    SEARCH (new SearchCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
