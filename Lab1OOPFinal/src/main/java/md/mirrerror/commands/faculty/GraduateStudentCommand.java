package md.mirrerror.commands.faculty;

import md.mirrerror.AppState;
import md.mirrerror.Main;
import md.mirrerror.commands.Command;
import md.mirrerror.entities.Student;

public class GraduateStudentCommand extends Command {
    public GraduateStudentCommand() {
        super("gs");
    }

    @Override
    public void onCommand(String[] args) {
        if(Main.getAppState() != AppState.FACULTY_OPERATIONS) {
            System.out.println("Switch to the faculty operations branch first.");
            return;
        }

        if(args.length < 1) {
            System.out.println("Usage: gs/<email> - graduate student");
            return;
        }

        Student student = Main.getDataRegistry().searchStudent(args[0]);
        if(student == null) {
            System.out.println("The student with the specified email doesn't exist.");
            return;
        }

        Main.getDataRegistry().graduateStudent(args[0]);
        System.out.println("Successfully graduated student " + student.getFirstName() + " " + student.getLastName() + ".");
    }
}
