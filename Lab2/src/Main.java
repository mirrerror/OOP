import commands.Command;
import commands.CommandParser;
import commands.CommandRegistry;
import commands.main.CommitCommand;
import commands.main.InfoCommand;
import commands.main.StatusCommand;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static boolean isAppEnabled;

    public static void main(String[] args) {
        isAppEnabled = true;
        Scanner scanner = new Scanner(System.in);
        CommandParser commandParser = new CommandParser();
        CommandRegistry commandRegistry = new CommandRegistry();

        commandRegistry.registerCommands(
                List.of(
                        new CommitCommand(),
                        new InfoCommand(),
                        new StatusCommand()
                )
        );

        while(isAppEnabled) {
            String commandString = scanner.nextLine();
            boolean commandFound = false;

            for(Command command : CommandRegistry.getRegisteredCommands()) {
                if(commandParser.getCommandLabel(commandString).equalsIgnoreCase(command.getLabel())) {
                    command.onCommand(commandParser.parseArguments(commandString));
                    commandFound = true;
                    break;
                }
            }

            if(!commandFound) System.out.println("Unknown command.");
        }

        scanner.close();
    }

    public static boolean isAppEnabled() {
        return isAppEnabled;
    }

    public static void setAppEnabled(boolean isAppEnabled) {
        Main.isAppEnabled = isAppEnabled;
    }
}