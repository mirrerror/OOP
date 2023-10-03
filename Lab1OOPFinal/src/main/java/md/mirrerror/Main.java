package md.mirrerror;

import md.mirrerror.commands.*;
import md.mirrerror.commands.faculty.*;
import md.mirrerror.commands.general.DisplayFacultiesCommand;
import md.mirrerror.commands.general.NewFacultyCommand;
import md.mirrerror.commands.general.SearchStudentCommand;
import md.mirrerror.commands.main.*;
import md.mirrerror.data.DataRegistry;
import md.mirrerror.data.FileManager;
import md.mirrerror.entities.AppState;
import md.mirrerror.logging.Logger;
import md.mirrerror.utils.MenuUtils;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static boolean isAppEnabled;
    private static AppState appState;
    private static DataRegistry dataRegistry;
    private static FileManager fileManager;
    private static Logger logger;

    public static void main(String[] args) {
        isAppEnabled = true;
        appState = AppState.MAIN_MENU;
        CommandParser commandParser = new CommandParser();
        CommandRegistry commandRegistry = new CommandRegistry();
        fileManager = new FileManager(DataRegistry.getFaculties());
        dataRegistry = new DataRegistry();
        Scanner scanner = new Scanner(System.in);
        logger = new Logger(new File("applogs.log"));
        logger.info("Started the app.");

        fileManager.loadData();
        logger.info("Loaded the data.");

        commandRegistry.registerCommands(
                List.of(
                        new QuitCommand(), new BackCommand(), new FacultyOperationsCommand(),
                        new GeneralOperationsCommand(), new StudentOperationsCommand(), new NewFacultyCommand(),
                        new SearchStudentCommand(), new DisplayFacultiesCommand(), new NewStudentCommand(),
                        new BelongsToFacultyCommand(), new DisplayGraduatedCommand(), new DisplayStudentsCommand(),
                        new GraduateStudentCommand(), new BatchUpdateCommand()
                )
        );
        logger.info("Registered the commands.");

        MenuUtils.sendMainMenuHelpMessage();

        while(isAppEnabled) executeCommand(commandParser, scanner.nextLine());

        scanner.close();

        logger.info("Stopped the app.");
    }

    public static void executeCommand(CommandParser commandParser, String commandString) {
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

    public static AppState getAppState() {
        return appState;
    }

    public static void setAppState(AppState appState) {
        Main.appState = appState;
    }

    public static DataRegistry getDataRegistry() {
        return dataRegistry;
    }

    public static boolean isIsAppEnabled() {
        return isAppEnabled;
    }

    public static void setAppEnabled(boolean isAppEnabled) {
        Main.isAppEnabled = isAppEnabled;
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static Logger getLogger() {
        return logger;
    }
}