package by.training.interpol.command;

public class DefaultCommand implements Command {
    private static final String INDEX_PAGE = "/index.jsp";
    @Override
    public ResponseType execute(SessionRequestContent content) {
        content.invalidateSession();
        return new ResponseType(INDEX_PAGE, SendType.REDIRECT);
    }
}
