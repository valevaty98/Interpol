package by.training.interpol.command;

import by.training.interpol.entity.User;
import by.training.interpol.logic.MessageLogic;

public class SendResponseToUserCommand implements Command {
    private static final String BACK_TO_FULL_MESSAGE_ACTION = "/controller?command=show_full_message&message_id=";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        long messageId = Long.parseLong(content.getFromRequestParameters("message_id")[0]);
        User user = (User)content.getFromSessionAttributes("user");
        long userId = user.getId();
        MessageLogic.updateMessageStatusToChecked(messageId);
        MessageLogic.incrementUsersNumberOfMessages(userId);
        return builder.buildResponseType(BACK_TO_FULL_MESSAGE_ACTION + messageId, SendType.REDIRECT);
    }
}
