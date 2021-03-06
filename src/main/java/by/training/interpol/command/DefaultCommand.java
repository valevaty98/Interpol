package by.training.interpol.command;

import by.training.interpol.util.PageServletPath;

public class DefaultCommand implements Command {
    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        content.invalidateSession();
        return builder.buildResponseType(PageServletPath.INDEX_PAGE, SendType.FORWARD);
    }
}
