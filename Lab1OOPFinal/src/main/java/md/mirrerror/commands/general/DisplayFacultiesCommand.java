package md.mirrerror.commands.general;

import md.mirrerror.data.DataValidator;
import md.mirrerror.entities.AppState;
import md.mirrerror.Main;
import md.mirrerror.commands.Command;
import md.mirrerror.entities.StudyField;

public class DisplayFacultiesCommand extends Command {
    public DisplayFacultiesCommand() {
        super("df");
    }

    @Override
    public void onCommand(String[] args) {
        StudyField studyField;

        if(Main.getAppState() != AppState.GENERAL_OPERATIONS) {
            System.out.println("Switch to the general operations branch first.");
            return;
        }

        if(args.length == 0) {
            Main.getDataRegistry().displayFaculties();
        } else {
            if(!DataValidator.validateStudyField(args[0])) return;
            studyField = StudyField.match(args[0]);
            Main.getDataRegistry().displayFaculties(studyField);
        }
    }
}
