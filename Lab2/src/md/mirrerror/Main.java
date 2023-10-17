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
import md.mirrerror.tasks.FileCheckTask;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static boolean isRunning;
    private static Repository repository;
    private static FileCheckTask fileCheckTask;

    public static void main(String[] args) {
        isRunning = true;
        Scanner scanner = new Scanner(System.in);
        CommandParser commandParser = new CommandParser();
        CommandRegistry commandRegistry = new CommandRegistry();
        RepositoryManager repositoryManager = new RepositoryManager();
        fileCheckTask = new FileCheckTask();

        commandRegistry.registerCommands(
                List.of(
                        new CommitCommand(),
                        new InfoCommand(),
                        new StatusCommand(),
                        new QuitCommand()
                )
        );

        repository = repositoryManager.registerRepository("testrepo");

        sendHelpMessage();

        fileCheckTask.initialize();

        while(isRunning) {

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

        fileCheckTask.cancel();
        fileCheckTask.getFileCheckTimer().cancel();
        scanner.close();
    }

    private static void sendHelpMessage() {
        System.out.println("""
                
                Welcome to the repository management application.
                
                Available commands:
                - commit - commit the changes
                - info <file name> - get info about a specific file from the repository
                - status - get info about the status of the files in the repository
                - q - exit the application
                
                """);
    }

    public static void setRunning(boolean isRunning) {
        Main.isRunning = isRunning;
    }

    public static Repository getRepository() {
        return repository;
    }

    public static FileCheckTask getFileCheckTask() {
        return fileCheckTask;
    }
}