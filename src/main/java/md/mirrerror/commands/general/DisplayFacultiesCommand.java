package md.mirrerror.commands.general;

import md.mirrerror.AppState;
import md.mirrerror.Main;
import md.mirrerror.commands.Command;
import md.mirrerror.entities.StudyField;

public class DisplayFacultiesCommand extends Command {
    public DisplayFacultiesCommand() {
        super("df");
    }

    @Override
    public void onCommand(String[] args) {
        if(Main.getAppState() != AppState.GENERAL_OPERATIONS) {
            System.out.println("Switch to the general operations branch first.");
            return;
        }

        if(args.length == 0) {
            Main.getDataRegistry().displayFaculties();
        } else {
            StudyField studyField;
            try {
                studyField = StudyField.match(args[0]);
            } catch (IllegalArgumentException ignored) {
                System.out.println("Invalid faculty name. Available ones are: MECHANICAL_ENGINEERING, SOFTWARE_ENGINEERING, FOOD_TECHNOLOGY, URBANISM_ARCHITECTURE, VETERINARY_MEDICINE.");
                return;
            }

            Main.getDataRegistry().displayFaculties(studyField);
        }
    }
}
