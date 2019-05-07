package by.training.interpol.command;

import by.training.interpol.logic.MessageLogic;

public class SendResponseToUserCommand implements Command {
    @Override
    public ResponseType execute(SessionRequestContent content) {
        long messageId = Long.parseLong(content.getFromRequestParameters("message_id")[0]);
        MessageLogic.updateMessageStatusToChecked(messageId);

        return new ShowFullMessageCommand().execute(content);
    }
}
