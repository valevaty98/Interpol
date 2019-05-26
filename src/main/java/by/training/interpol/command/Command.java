package by.training.interpol.command;

/**
 * Command for processing requests
 */
public interface Command {
    /**
     * @param content request content for extracting parameters
     * @return type of response wrapper
     */
    ResponseType execute(SessionRequestContent content);
}
