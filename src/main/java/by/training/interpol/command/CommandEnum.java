package by.training.interpol.command;

public enum CommandEnum {
    DEFAULT (new DefaultCommand()),
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
    DELETE_PERSON (new DeletePersonCommand()),
    SHOW_ALL_MESSAGES (new ShowAllMessagesBriefCommand()),
    SHOW_FULL_MESSAGE (new ShowFullMessageCommand()),
    SEND_RESPONSE_TO_USER(new SendResponseToUserCommand()),
    CHANGE_LANG(new ChangeLanguageCommand()),
    SET_ASSESSMENT(new SetAssessmentCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
