package md.mirrerror.commands.faculty;

import md.mirrerror.AppState;
import md.mirrerror.Main;
import md.mirrerror.commands.Command;
import md.mirrerror.entities.Faculty;

public class DisplayStudentsCommand extends Command {
    public DisplayStudentsCommand() {
        super("ds");
    }

    @Override
    public void onCommand(String[] args) {
        if(Main.getAppState() != AppState.FACULTY_OPERATIONS) {
            System.out.println("Switch to the faculty operations branch first.");
            return;
        }

        if(args.length < 1) {
            System.out.println("Usage: ds/<faculty abbreviation> - display enrolled students");
            return;
        }

        Faculty faculty = Main.getDataRegistry().searchFacultyByAbbreviation(args[0]);
        if(faculty == null) {
            System.out.println("The specified faculty doesn't exist.");
            return;
        }

        Main.getDataRegistry().displayStudents(faculty);
    }
}
