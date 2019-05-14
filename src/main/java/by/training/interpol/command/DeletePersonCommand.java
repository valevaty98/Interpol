package by.training.interpol.command;

import by.training.interpol.logic.DeletePersonLogic;

public class DeletePersonCommand implements Command {
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        long personId = Long.parseLong(content.getFromRequestParameters("person_id")[0]);
        DeletePersonLogic.deletePersonById(personId);
        return builder.buildResponseType(MAIN_PAGE_PATH, SendType.FORWARD);
    }
}
