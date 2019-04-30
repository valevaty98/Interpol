package by.training.interpol.command;

public class LogoutCommand implements Command {
    @Override
    public ResponseType execute(SessionRequestContent content) {
        content.putInRequestAttributes("lastUser", content.getFromSessionAttributes("user"));
        content.invalidateSession();
        System.out.println("logout");
        return new ResponseType("index.jsp", SendType.FORWARD);
    }
}
