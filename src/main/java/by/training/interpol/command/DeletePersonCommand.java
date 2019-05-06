package by.training.interpol.command;

import by.training.interpol.logic.DeletePersonLogic;

public class DeletePersonCommand implements Command {
    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        long personId = Long.parseLong(content.getFromRequestParameters("person_id")[0]);

        boolean isDeleted = DeletePersonLogic.deletePersonById(personId);

        return new HomeCommand().execute(content);
    }
}
