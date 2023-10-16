package commands;

public class CommandParser {

    private final static String COMMAND_DELIMITER = " ";

    public String[] parseArguments(String s) {
        String[] rawArgs = s.split(COMMAND_DELIMITER);
        String[] args = new String[rawArgs.length-1];
        System.arraycopy(rawArgs, 1, args, 0, rawArgs.length - 1);
        return args;
    }

    public String getCommandLabel(String s) {
        return s.split(COMMAND_DELIMITER)[0];
    }

}
