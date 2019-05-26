package by.training.interpol.command;

import by.training.interpol.entity.Role;
import by.training.interpol.entity.User;
import by.training.interpol.util.AttributeParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {
    private static Logger logger = LogManager.getLogger();
    private final static String ERROR_ATTR = "wrongCommand";

    public Command defineCommand(SessionRequestContent content) {
        Command command;
        String[] stringCommands = content.getFromRequestParameters(AttributeParameterName.COMMAND_PARAM);
        String stringCommand;

        if (stringCommands != null) {
            stringCommand = stringCommands[0];
        } else {
            logger.log(Level.ERROR, "No command parameter");
            return new DefaultCommand();
        }
        try {
            command = CommandEnum.valueOf(stringCommand.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e) {
            logger.log(Level.ERROR, "No such command in the command enum!", e);
            content.putInRequestAttributes(ERROR_ATTR, "No such command: " + stringCommand);
            if (content.getFromSessionAttributes(AttributeParameterName.USER_ATTR) != null) {
                command = new HomeCommand();
            } else {
                command = new DefaultCommand();
            }
        }
        return command;
    }
}
