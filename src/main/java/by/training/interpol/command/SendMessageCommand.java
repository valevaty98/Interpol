package by.training.interpol.command;

import by.training.interpol.entity.Message;
import by.training.interpol.entity.MessageStatus;
import by.training.interpol.entity.User;
import by.training.interpol.logic.MessageLogic;
import by.training.interpol.util.AttributeParameterName;
import by.training.interpol.util.PageServletPath;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SendMessageCommand implements Command {
    private static final String TO_FULL_PERSON_ACTION = "?command=show_full_person&person_id=";
    private static final String ERROR_ATTR = "sendMessageError";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final MessageStatus DEFAULT_MESSAGE_STATUS = MessageStatus.UNCHECKED;

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        String[] messageTextParams = content.getFromRequestParameters(AttributeParameterName.MESSAGE_PARAM);
        String[] subjectParams = content.getFromRequestParameters(AttributeParameterName.SUBJECT_PARAM);
        String[] wantedPersonIdParams = content.getFromRequestParameters(AttributeParameterName.WANTED_PERSON_ID_PARAM);
        String messageText = (messageTextParams != null) ? messageTextParams[0] : null;
        String subject = (subjectParams != null) ? subjectParams[0] : null;
        Long wantedPersonId;
        try {
            wantedPersonId = (wantedPersonIdParams != null) ? Long.parseLong(wantedPersonIdParams[0]) : null;
        } catch (NumberFormatException e) {
            content.putInRequestAttributes(ERROR_ATTR, "Invalid wanted person id!");
            return builder.buildResponseType(PageServletPath.SEND_MESSAGE_PAGE, SendType.FORWARD);
        }
        User user = (User)content.getFromSessionAttributes(AttributeParameterName.USER_ATTR);
        long userId = user.getId();
        Date date = new Date();
        String currentTime = new SimpleDateFormat(DATE_TIME_FORMAT).format(date);
        Message message = new Message(subject, messageText, currentTime, wantedPersonId, userId, DEFAULT_MESSAGE_STATUS);
        if (MessageLogic.sendMessage(message)) {
            return builder.buildResponseType(PageServletPath.FRONT_CONTROLLER + TO_FULL_PERSON_ACTION + wantedPersonId, SendType.REDIRECT);
        } else {
            content.putInRequestAttributes(ERROR_ATTR, "Message isn't sent!");
            return builder.buildResponseType(PageServletPath.SEND_MESSAGE_PAGE, SendType.FORWARD);
        }
    }
}
