package by.training.interpol.command;

public enum CommandEnum {
    LOGIN (new LoginCommand()),
    SIGNUP (new SignUpCommand()),
    HOME (new HomeCommand()),
    LOGOUT (new LogoutCommand()),
    SEARCH (new SearchCommand()),
    GET_PAGE(new GetPageCommand()),
    EDIT_EMAIL(new EditEmailCommand()),
    SHOW_FULL_PERSON (new ShowFullPersonCommand()),
    SEND_MESSAGE (new SendMessageCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}