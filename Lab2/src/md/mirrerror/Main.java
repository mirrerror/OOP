package md.mirrerror;

import md.mirrerror.commands.Command;
import md.mirrerror.commands.CommandParser;
import md.mirrerror.commands.CommandRegistry;
import md.mirrerror.commands.main.CommitCommand;
import md.mirrerror.commands.main.InfoCommand;
import md.mirrerror.commands.main.QuitCommand;
import md.mirrerror.commands.main.StatusCommand;
import md.mirrerror.entities.Repository;
import md.mirrerror.managers.RepositoryManager;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static boolean isAppEnabled;
    private static Repository repository;

    public static void main(String[] args) {
        isAppEnabled = true;
        Scanner scanner = new Scanner(System.in);
        CommandParser commandParser = new CommandParser();
        CommandRegistry commandRegistry = new CommandRegistry();
        RepositoryManager repositoryManager = new RepositoryManager();

        commandRegistry.registerCommands(
                List.of(
                        new CommitCommand(),
                        new InfoCommand(),
                        new StatusCommand(),
                        new QuitCommand()
                )
        );

        repository = repositoryManager.registerRepository(new File("testrepo"));

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

    public static Repository getRepository() {
        return repository;
    }
}