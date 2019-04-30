package by.training.interpol.command;

public interface Command {
    ResponseType execute(SessionRequestContent content);
}
