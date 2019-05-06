package by.training.interpol.command;

public enum CommandEnum {
    LOGIN (new LoginCommand()),
    SIGN_UP (new SignUpCommand()),
    HOME (new HomeCommand()),
    LOGOUT (new LogoutCommand()),
    SEARCH (new SearchCommand()),
    GET_PAGE(new GetPageCommand()),
    EDIT_EMAIL(new EditEmailCommand()),
    SHOW_FULL_PERSON (new ShowFullPersonCommand()),
    SEND_MESSAGE (new SendMessageCommand()),
    ADD_WANTED_PERSON (new AddWantedPersonCommand()),
    DELETE_PERSON (new DeletePersonCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
