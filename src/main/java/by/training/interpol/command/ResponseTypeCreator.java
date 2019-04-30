package by.training.interpol.command;

public class ResponseTypeCreator {
    public ResponseType buildResponseType(String page, SendType sendType) {
        return new ResponseType(page, sendType);
    }
}
