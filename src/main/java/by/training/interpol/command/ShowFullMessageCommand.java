package by.training.interpol.command;

import by.training.interpol.entity.FullMessageInfo;
import by.training.interpol.logic.MessageLogic;
import by.training.interpol.util.AttributeParameterName;
import by.training.interpol.util.PageServletPath;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ShowFullMessageCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        Optional<FullMessageInfo> messageInfo;
        String[] messageIdParams = content.getFromRequestParameters(AttributeParameterName.MESSAGE_ID_PARAM);
        Long messageId;
        try {
            messageId = (messageIdParams != null) ? Long.parseLong(messageIdParams[0]) : null;
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Illegal message id param for parsing " + messageIdParams[0], e);
            return new HomeCommand().execute(content);
        }
        messageInfo = MessageLogic.receiveFullMessage(messageId);
        if(messageInfo.isPresent()) {
            content.putInRequestAttributes(AttributeParameterName.MESSAGE_ATTR, messageInfo.get());
            return builder.buildResponseType(PageServletPath.FULL_MESSAGE_PAGE, SendType.FORWARD);
        } else {
            logger.log(Level.ERROR, "Can't receive no message info.");
            return new HomeCommand().execute(content);
        }
    }
}
