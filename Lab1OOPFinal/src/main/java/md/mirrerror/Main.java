package md.mirrerror;

import md.mirrerror.commands.*;
import md.mirrerror.commands.faculty.*;
import md.mirrerror.commands.general.DisplayFacultiesCommand;
import md.mirrerror.commands.general.NewFacultyCommand;
import md.mirrerror.commands.general.SearchStudentCommand;
import md.mirrerror.commands.main.*;
import md.mirrerror.data.DataRegistry;
import md.mirrerror.entities.AppState;
import md.mirrerror.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static boolean isAppEnabled;
    private static AppState appState;
    private static DataRegistry dataRegistry;

    public static void main(String[] args) {
        isAppEnabled = true;
        appState = AppState.MAIN_MENU;
        CommandParser commandParser = new CommandParser();
        CommandRegistry commandRegistry = new CommandRegistry();
        dataRegistry = new DataRegistry();
        Scanner scanner = new Scanner(System.in);

        dataRegistry.loadData();

        commandRegistry.registerCommands(
                List.of(
                        new QuitCommand(), new BackCommand(), new FacultyOperationsCommand(),
                        new GeneralOperationsCommand(), new StudentOperationsCommand(), new NewFacultyCommand(),
                        new SearchStudentCommand(), new DisplayFacultiesCommand(), new NewStudentCommand(),
                        new BelongsToFacultyCommand(), new DisplayGraduatedCommand(), new DisplayStudentsCommand(),
                        new GraduateStudentCommand()
                )
        );

        MenuUtils.sendMainMenu();

        while(isAppEnabled) {

            boolean commandFound = false;
            String entry = scanner.nextLine();
            for(Command command : CommandRegistry.getRegisteredCommands()) {
                if(commandParser.getCommandLabel(entry).equalsIgnoreCase(command.getLabel())) {
                    command.onCommand(commandParser.parseArguments(entry));
                    commandFound = true;
                    break;
                }
            }

            if(!commandFound) System.out.println("Unknown command.");

        }

        scanner.close();

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
}