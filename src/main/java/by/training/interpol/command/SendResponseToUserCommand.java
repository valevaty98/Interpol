package by.training.interpol.command;

import by.training.interpol.entity.FullMessageInfo;
import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.MessageLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;

import java.util.List;
import java.util.Optional;

public class SendResponseToUserCommand implements Command {
    private static final String FULL_MESSAGE_PAGE_PATH = "/jsp/full_message.jsp";
    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        Optional<FullMessageInfo> messageInfo;
        List<WantedPerson> wantedPeople;
        List<String> nationalities;
        long messageId = Long.parseLong(content.getFromRequestParameters("message_id")[0]);
        MessageLogic.updateMessageStatusToChecked(messageId);
        messageInfo = MessageLogic.receiveFullMessage(messageId);
        if(messageInfo.isPresent()) {
            wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleBrief();
            nationalities = ReceiveWantedPersonInfoLogic.receiveNationalityList();
            content.putInSessionAttributes("wantedPeople", wantedPeople);
            content.putInSessionAttributes("nationalities", nationalities);
            content.putInRequestAttributes("message", messageInfo.get());
            return builder.buildResponseType(FULL_MESSAGE_PAGE_PATH, SendType.REDIRECT);
        } else {
            return new HomeCommand().execute(content);
        }
    }
}
