package by.training.interpol.command;

public enum CommandEnum {
    LOGIN (new LoginCommand()),
    SIGNUP (new SignUpCommand()),
    LOGOUT (new LogoutCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
