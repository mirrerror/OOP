package md.mirrerror.commands.general;

import md.mirrerror.AppState;
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
        if(Main.getAppState() != AppState.GENERAL_OPERATIONS) {
            System.out.println("Switch to the general operations branch first.");
            return;
        }

        if(args.length < 3) {
            System.out.println("Usage: nf/<faculty name>/<faculty abbreviation>/<field> - create faculty");
            return;
        }

        Faculty test1 = Main.getDataRegistry().searchFacultyByName(args[0]);
        if(test1 != null) {
            System.out.println("A faculty with the specified name already exist.");
            return;
        }

        Faculty test2 = Main.getDataRegistry().searchFacultyByAbbreviation(args[1]);
        if(test2 != null) {
            System.out.println("A faculty with the specified abbreviation already exist.");
            return;
        }

        StudyField studyField;
        try {
            studyField = StudyField.match(args[2]);
        } catch (IllegalArgumentException ignored) {
            System.out.println("Invalid faculty name. Available ones are: MECHANICAL_ENGINEERING, SOFTWARE_ENGINEERING, FOOD_TECHNOLOGY, URBANISM_ARCHITECTURE, VETERINARY_MEDICINE.");
            return;
        }

        Faculty faculty = new Faculty(args[0], args[1], studyField);
        Main.getDataRegistry().addNewFaculty(faculty);
        System.out.println("Successfully registered a new faculty with name \"" + faculty.getName() + "\".");
    }
}
