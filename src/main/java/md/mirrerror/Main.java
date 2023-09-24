package md.mirrerror;

import md.mirrerror.commands.*;
import md.mirrerror.commands.faculty.*;
import md.mirrerror.commands.general.DisplayFacultiesCommand;
import md.mirrerror.commands.general.NewFacultyCommand;
import md.mirrerror.commands.general.SearchStudentCommand;
import md.mirrerror.commands.main.*;

import java.util.Scanner;

public class Main {
    private static AppState appState;
    private static DataRegistry dataRegistry;

    public static void main(String[] args) {
        appState = AppState.MAIN_MENU;
        CommandParser commandParser = new CommandParser();
        CommandRegistry commandRegistry = new CommandRegistry();
        dataRegistry = new DataRegistry();

        dataRegistry.loadData();

        commandRegistry.registerCommand(new QuitCommand());
        commandRegistry.registerCommand(new BackCommand());
        commandRegistry.registerCommand(new FacultyOperationsCommand());
        commandRegistry.registerCommand(new GeneralOperationsCommand());
        commandRegistry.registerCommand(new StudentOperationsCommand());
        commandRegistry.registerCommand(new NewFacultyCommand());
        commandRegistry.registerCommand(new SearchStudentCommand());
        commandRegistry.registerCommand(new DisplayFacultiesCommand());
        commandRegistry.registerCommand(new NewStudentCommand());
        commandRegistry.registerCommand(new BelongsToFacultyCommand());
        commandRegistry.registerCommand(new DisplayGraduatedCommand());
        commandRegistry.registerCommand(new DisplayStudentsCommand());
        commandRegistry.registerCommand(new GraduateStudentCommand());

        System.out.println("Welcome to TUM's student management system!");
        System.out.println("What do you want to do?");
        System.out.println("g - General operations");
        System.out.println("f - Faculty operations");
        System.out.println("s - Student operations");
        System.out.println();
        System.out.println("q - Quit program");

        while(true) {

            boolean commandFound = false;
            Scanner scanner = new Scanner(System.in);
            String entry = scanner.next();
            for(Command command : CommandRegistry.getRegisteredCommands()) {
                if(commandParser.getCommandLabel(entry).equalsIgnoreCase(command.getLabel())) {
                    command.onCommand(commandParser.parseArguments(entry));
                    commandFound = true;
                    break;
                }
            }

            if(!commandFound) System.out.println("Unknown command.");

        }

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
}