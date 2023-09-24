package md.mirrerror.commands.general;

import md.mirrerror.AppState;
import md.mirrerror.Main;
import md.mirrerror.commands.Command;
import md.mirrerror.entities.Faculty;

public class SearchStudentCommand extends Command {
    public SearchStudentCommand() {
        super("ss");
    }

    @Override
    public void onCommand(String[] args) {
        if(Main.getAppState() != AppState.GENERAL_OPERATIONS) {
            System.out.println("Switch to the general operations branch first.");
            return;
        }

        if(args.length < 1) {
            System.out.println("Usage: ss/<student email> - search student and show faculty");
            return;
        }

        Faculty faculty = Main.getDataRegistry().searchFacultyByStudentEmail(args[0]);

        if(faculty == null) {
            System.out.println("Student with the specified email is not present in any currently registered faculties.");
        } else {
            System.out.println("Faculty: " + faculty.getName() + " (" + faculty.getAbbreviation() + ").");
        }
    }
}
