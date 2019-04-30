package by.training.interpol.command;

public class CommandFactory {
    private static final String DEFAULT_COMMAND_PARAM_NAME = "command";

    public Command defineCommand(SessionRequestContent content) {
        Command command;
        String[] stringCommands = content.getFromRequestParameters(DEFAULT_COMMAND_PARAM_NAME);
        String stringCommand;

        if (stringCommands != null && stringCommands.length >= 1) {
            stringCommand = stringCommands[0];
        } else {
            //todo - log
            return new DefaultCommand();
        }
        try {
            command = CommandEnum.valueOf(stringCommand.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e) {
            content.putInRequestAttributes("wrongAction", stringCommand);
            command = new DefaultCommand();
        }
        return command;
    }
}
