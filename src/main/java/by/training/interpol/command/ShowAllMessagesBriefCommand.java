package by.training.interpol.command;

import by.training.interpol.entity.BriefMessageInfo;
import by.training.interpol.logic.MessageLogic;
import by.training.interpol.util.AttributeParameterName;
import by.training.interpol.util.PageServletPath;

import java.util.List;

public class ShowAllMessagesBriefCommand implements Command {


    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        List<BriefMessageInfo> messageInfoList;
        messageInfoList = MessageLogic.receiveAllMessagesBrief();
        content.putInRequestAttributes(AttributeParameterName.MESSAGE_INFO_LIST_ATTR, messageInfoList);
        return builder.buildResponseType(PageServletPath.ALL_MESSAGES_PAGE, SendType.FORWARD);
    }
}
