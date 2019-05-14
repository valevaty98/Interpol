package by.training.interpol.command;

import by.training.interpol.entity.FullMessageInfo;
import by.training.interpol.entity.User;
import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.MessageLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;

import java.util.List;
import java.util.Optional;

public class SendResponseToUserCommand implements Command {
    private static final String BACK_TO_FULL_MESSAGE_ACTION = "/controller?command=show_full_message&message_id=";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        Optional<FullMessageInfo> messageInfo;
        //List<WantedPerson> wantedPeople;
        //List<String> nationalities;
        long messageId = Long.parseLong(content.getFromRequestParameters("message_id")[0]);
        User user = (User)content.getFromSessionAttributes("user");
        long userId = user.getId();
        MessageLogic.updateMessageStatusToChecked(messageId);
        MessageLogic.incrementUsersNumberOfMessages(userId);
        //if(messageInfo.isPresent()) {
           // wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleBrief();
            //nationalities = ReceiveWantedPersonInfoLogic.receiveNationalityList();
//            content.putInSessionAttributes("wantedPeople", wantedPeople);
//            content.putInSessionAttributes("nationalities", nationalities);
            return builder.buildResponseType(BACK_TO_FULL_MESSAGE_ACTION + messageId, SendType.REDIRECT);
//        } else {
//            return new HomeCommand().execute(content);
//        }
    }
}
