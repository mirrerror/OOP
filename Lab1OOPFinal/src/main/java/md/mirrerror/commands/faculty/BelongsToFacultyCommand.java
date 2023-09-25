package md.mirrerror.commands.faculty;

import md.mirrerror.AppState;
import md.mirrerror.Main;
import md.mirrerror.commands.Command;
import md.mirrerror.entities.Faculty;

public class BelongsToFacultyCommand extends Command {
    public BelongsToFacultyCommand() {
        super("bf");
    }

    @Override
    public void onCommand(String[] args) {
        if(Main.getAppState() != AppState.FACULTY_OPERATIONS) {
            System.out.println("Switch to the faculty operations branch first.");
            return;
        }

        if(args.length < 2) {
            System.out.println("Usage: bf/<faculty abbreviation>/<email> - check if student belongs to faculty");
            return;
        }

        Faculty test1 = Main.getDataRegistry().searchFacultyByAbbreviation(args[0]);
        if(test1 == null) {
            System.out.println("Faculty with the specified abbreviation doesn't exist.");
            return;
        }

        Faculty faculty = Main.getDataRegistry().searchFacultyByStudentEmail(args[1]);
        if(faculty == null) {
            System.out.println("The specified student doesn't belong to the specified faculty.");
        } else {
            System.out.println("The specified student belongs to the specified faculty.");
        }
    }
}
