package by.training.interpol.command;

import by.training.interpol.entity.BriefMessageInfo;
import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.MessageLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;

import java.util.List;

public class ShowAllMessagesBriefCommand implements Command {
    private static final String ALL_MESSAGES_PAGE_PATH = "/jsp/all_users_messages.jsp";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        List<BriefMessageInfo> messageInfoList;
        messageInfoList = MessageLogic.receiveAllMessagesBrief();
        content.putInRequestAttributes("messages_info_list", messageInfoList);
        return builder.buildResponseType(ALL_MESSAGES_PAGE_PATH, SendType.FORWARD);
    }
}
