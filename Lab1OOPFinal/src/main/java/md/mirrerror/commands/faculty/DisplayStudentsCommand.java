package md.mirrerror.commands.faculty;

import md.mirrerror.data.DataValidator;
import md.mirrerror.entities.AppState;
import md.mirrerror.Main;
import md.mirrerror.commands.Command;
import md.mirrerror.entities.Faculty;

public class DisplayStudentsCommand extends Command {
    public DisplayStudentsCommand() {
        super("ds");
    }

    @Override
    public void onCommand(String[] args) {
        Faculty faculty;

        if(Main.getAppState() != AppState.FACULTY_OPERATIONS_MENU) {
            System.out.println("Switch to the faculty operations branch first.");
            return;
        }

        if(args.length < 1) {
            System.out.println("Usage: ds/<faculty abbreviation> - display enrolled students");
            return;
        }

        faculty = Main.getDataRegistry().searchFacultyByAbbreviation(args[0]);

        if(!DataValidator.validateFaculty(faculty, true)) return;

        Main.getDataRegistry().displayStudents(faculty);
    }
}
