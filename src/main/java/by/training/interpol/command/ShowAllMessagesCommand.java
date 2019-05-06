package by.training.interpol.command;

import by.training.interpol.entity.FullMessageInfo;
import by.training.interpol.entity.User;
import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.LoginLogic;
import by.training.interpol.logic.MessageLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;

import java.util.List;
import java.util.Optional;

public class ShowAllMessagesCommand implements Command {
    private static final String ALL_MESSAGES_PAGE_PATH = "/jsp/all_users_messages.jsp";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        List<FullMessageInfo> messageInfoList;
        List<WantedPerson> wantedPeople;
        List<String> nationalities;

        messageInfoList = MessageLogic.receiveAllMessagesBrief();
        wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleBrief();
        nationalities = ReceiveWantedPersonInfoLogic.receiveNationalityList();
        content.putInSessionAttributes("wantedPeople", wantedPeople);
        content.putInSessionAttributes("nationalities", nationalities);
        content.putInRequestAttributes("messages_info_list", messageInfoList);

        return builder.buildResponseType(ALL_MESSAGES_PAGE_PATH, SendType.FORWARD);
    }
}
