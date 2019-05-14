package by.training.interpol.command;

public class DefaultCommand implements Command {
    private static final String INDEX_PAGE = "/index.jsp";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        content.invalidateSession();
        return builder.buildResponseType(INDEX_PAGE, SendType.FORWARD);
    }
}
