package by.training.interpol.command;

import by.training.interpol.entity.Message;
import by.training.interpol.entity.MessageStatus;
import by.training.interpol.entity.User;
import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.MessageLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SendMessageCommand implements Command {
    private static final String BACK_TO_FULL_PERSON_ACTION = "/controller?command=show_full_person&person_id=";
    private static final String SEND_MESSAGE_PAGE_PATH = "/jsp/send_message.jsp";
    private static final MessageStatus DEFAULT_MESSAGE_STATUS = MessageStatus.UNCHECKED;
    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        String[] messageTextParams = content.getFromRequestParameters("message");
        String[] subjectParams = content.getFromRequestParameters("subject");
        String[] wantedPersonIdParams = content.getFromRequestParameters("person_id");
        String messageText = (messageTextParams != null) ? messageTextParams[0] : null;
        String subject = (subjectParams != null) ? subjectParams[0] : null;
        Long wantedPersonId;
        try {
            wantedPersonId = (wantedPersonIdParams != null) ? Long.parseLong(wantedPersonIdParams[0]) : null;
        } catch (NumberFormatException e) {
            content.putInRequestAttributes("send_message_error", "Invalid wanted person id!");
            return builder.buildResponseType(SEND_MESSAGE_PAGE_PATH, SendType.FORWARD);
        }
        User user = (User)content.getFromSessionAttributes("user");
        long userId = user.getId();
        Date date = new Date();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        Message message = new Message(subject, messageText, currentTime, wantedPersonId, userId, DEFAULT_MESSAGE_STATUS);
        if (MessageLogic.sendMessage(message)) {
            return builder.buildResponseType(BACK_TO_FULL_PERSON_ACTION + wantedPersonId, SendType.REDIRECT);
        } else {
            content.putInRequestAttributes("send_message_error", "Message isn't sent!");
            return builder.buildResponseType(SEND_MESSAGE_PAGE_PATH, SendType.FORWARD);
        }
    }
}
