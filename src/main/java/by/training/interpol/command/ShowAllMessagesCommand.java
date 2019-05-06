package by.training.interpol.command;

import by.training.interpol.entity.BriefMessageInfo;
import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.MessageLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;

import java.util.List;

public class ShowAllMessagesCommand implements Command {
    private static final String ALL_MESSAGES_PAGE_PATH = "/jsp/all_users_messages.jsp";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        List<BriefMessageInfo> messageInfoList;
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
