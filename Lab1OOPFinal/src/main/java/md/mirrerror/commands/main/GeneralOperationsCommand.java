package md.mirrerror.commands.main;

import md.mirrerror.AppState;
import md.mirrerror.Main;
import md.mirrerror.commands.Command;

public class GeneralOperationsCommand extends Command {
    public GeneralOperationsCommand() {
        super("g");
    }

    @Override
    public void onCommand(String[] args) {
        Main.setAppState(AppState.GENERAL_OPERATIONS);
        System.out.println();
        System.out.println("General operations");
        System.out.println("What do you want to do?");
        System.out.println();
        System.out.println("nf/<faculty name>/<faculty abbreviation>/<field> - create faculty");
        System.out.println("ss/<student email> - search student and show faculty");
        System.out.println("df - display faculties");
        System.out.println("df/field - display all faculties of a field");
        System.out.println();
        System.out.println("b - Back");
        System.out.println("q - Quit Program");
    }
}
