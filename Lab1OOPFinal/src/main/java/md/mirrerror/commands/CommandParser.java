package md.mirrerror.commands;

public class CommandParser {

    public String[] parseArguments(String s) {
        String[] rawArgs = s.split("/");
        String[] args = new String[rawArgs.length-1];
        System.arraycopy(rawArgs, 1, args, 0, rawArgs.length - 1);
        return args;
    }

    public String getCommandLabel(String s) {
        return s.split("/")[0];
    }

}
