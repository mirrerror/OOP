package md.mirrerror.commands.main;

import md.mirrerror.AppState;
import md.mirrerror.Main;
import md.mirrerror.commands.Command;

public class StudentOperationsCommand extends Command {
    public StudentOperationsCommand() {
        super("s");
    }

    @Override
    public void onCommand(String[] args) {
        Main.setAppState(AppState.STUDENT_OPERATIONS);
        System.out.println();
        System.out.println("Student operations");
        System.out.println("What do you want to do?");
        System.out.println();
        System.out.println("No options available at the moment.");
        System.out.println();
        System.out.println("b - Back");
        System.out.println("q - Quit Program");
    }
}
