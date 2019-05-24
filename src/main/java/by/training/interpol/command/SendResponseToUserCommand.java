package by.training.interpol.command;

import by.training.interpol.entity.User;
import by.training.interpol.logic.MessageLogic;
import by.training.interpol.util.AttributeParameterName;
import by.training.interpol.util.PageServletPath;

public class SendResponseToUserCommand implements Command {
    private static final String TO_FULL_MESSAGE_ACTION = "?command=show_full_message&message_id=";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        String[] messageIdParams = content.getFromRequestParameters(AttributeParameterName.MESSAGE_ID_PARAM);
        Long messageId;
        try {
            messageId = (messageIdParams != null) ? Long.parseLong(messageIdParams[0]) : null;
        } catch (NumberFormatException e) {
            return new HomeCommand().execute(content);
        }
        User user = (User)content.getFromSessionAttributes(AttributeParameterName.USER_ATTR);
        long userId = user.getId();
        MessageLogic.updateMessageStatusToChecked(messageId);
        MessageLogic.incrementUsersNumberOfMessages(userId);
        return builder.buildResponseType(PageServletPath.FRONT_CONTROLLER + TO_FULL_MESSAGE_ACTION + messageId, SendType.REDIRECT);
    }
}
