package md.mirrerror.commands.general;

import md.mirrerror.data.DataValidator;
import md.mirrerror.entities.AppState;
import md.mirrerror.Main;
import md.mirrerror.commands.Command;
import md.mirrerror.entities.Faculty;
import md.mirrerror.entities.StudyField;

public class NewFacultyCommand extends Command {
    public NewFacultyCommand() {
        super("nf");
    }

    @Override
    public void onCommand(String[] args) {
        Faculty faculty;
        StudyField studyField;

        if(Main.getAppState() != AppState.GENERAL_OPERATIONS) {
            System.out.println("Switch to the general operations branch first.");
            return;
        }

        if(args.length < 3) {
            System.out.println("Usage: nf/<faculty name>/<faculty abbreviation>/<field> - create faculty");
            return;
        }

        if(!DataValidator.validateFaculty(Main.getDataRegistry().searchFacultyByName(args[0]), false) ||
        !DataValidator.validateFaculty(Main.getDataRegistry().searchFacultyByAbbreviation(args[1]), false)) return;

        if(!DataValidator.validateStudyField(args[2])) return;

        studyField = StudyField.match(args[2]);
        faculty = new Faculty(args[0], args[1], studyField);

        Main.getDataRegistry().addNewFaculty(faculty);
        System.out.println("Successfully registered a new faculty with name \"" + faculty.getName() + "\".");
    }
}
