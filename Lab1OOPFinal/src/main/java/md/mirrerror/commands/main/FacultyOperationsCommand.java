package md.mirrerror.commands.main;

import md.mirrerror.AppState;
import md.mirrerror.Main;
import md.mirrerror.commands.Command;

public class FacultyOperationsCommand extends Command {
    public FacultyOperationsCommand() {
        super("f");
    }

    @Override
    public void onCommand(String[] args) {
        Main.setAppState(AppState.FACULTY_OPERATIONS);
        System.out.println();
        System.out.println("Faculty operations");
        System.out.println("What do you want to do?");
        System.out.println();
        System.out.println("ns/<faculty abbreviation>/<first name>/<last name>/<email>/<day>/<month>/<year> - create student");
        System.out.println("gs/<email> - graduate student");
        System.out.println("ds/<faculty abbreviation> - display enrolled students");
        System.out.println("dg/<faculty abbreviation> - display graduated students");
        System.out.println("bf/<faculty abbreviation>/<email> - check if student belongs to faculty");
        System.out.println();
        System.out.println("b - Back");
        System.out.println("q - Quit Program");
    }
}
