package by.training.interpol.command;

import by.training.interpol.entity.Message;
import by.training.interpol.entity.User;
import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.SendMessageLogic;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SendMessageCommand implements Command {
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";
    private static final String SEND_MESSAGE_PAGE_PATH = "/jsp/send_message.jsp";
    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        String messageText = content.getFromRequestParameters("message")[0];
        String subject = content.getFromRequestParameters("subject")[0];
        WantedPerson wantedPerson = (WantedPerson)content.getFromSessionAttributes("wantedPerson");
        long wantedPersonId = wantedPerson.getId();
        User user = (User)content.getFromSessionAttributes("user");
        long userId = user.getId();
        Date date = new Date();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        Message message = new Message(subject, messageText, currentTime, wantedPersonId, userId);
        if (SendMessageLogic.sendMessage(message)) {
            user.getAssessment().setNumberOfMessages(user.getAssessment().getNumberOfMessages() + 1);
            content.putInSessionAttributes("user", user);
            return builder.buildResponseType(MAIN_PAGE_PATH, SendType.REDIRECT);
        } else {
            content.putInRequestAttributes("send_message_error", "Message isn't sent!");
            return builder.buildResponseType(SEND_MESSAGE_PAGE_PATH, SendType.FORWARD);
        }
    }
}
