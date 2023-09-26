package md.mirrerror.commands.faculty;

import md.mirrerror.data.DataValidator;
import md.mirrerror.entities.AppState;
import md.mirrerror.Main;
import md.mirrerror.commands.Command;
import md.mirrerror.entities.Faculty;
import md.mirrerror.entities.Student;

import java.time.LocalDate;

public class NewStudentCommand extends Command {
    public NewStudentCommand() {
        super("ns");
    }

    @Override
    public void onCommand(String[] args) {
        int year, month, day;
        Student student;
        Faculty faculty;

        if(Main.getAppState() != AppState.FACULTY_OPERATIONS) {
            System.out.println("Switch to the faculty operations branch first.");
            return;
        }

        if(args.length < 7) {
            System.out.println("Usage: ns/<faculty abbreviation>/<first name>/<last name>/<email>/<day>/<month>/<year> - create student");
            return;
        }

        if(!DataValidator.validateEmailString(args[3])) return;

        if(!DataValidator.validateStudent(Main.getDataRegistry().searchStudent(args[3]), false)) return;

        if(!DataValidator.validateDate(args[6], args[5], args[4])) return;

        year = Integer.parseInt(args[6]);
        month = Integer.parseInt(args[5]);
        day = Integer.parseInt(args[4]);
        student = new Student(args[1], args[2], args[3], LocalDate.now(), LocalDate.of(year, month, day), false);
        faculty = Main.getDataRegistry().searchFacultyByAbbreviation(args[0]);

        if(!DataValidator.validateFaculty(faculty, true)) return;

        Main.getDataRegistry().addStudentToFaculty(student, args[0]);
        System.out.println("Successfully added a new student to the faculty " + faculty.getName() + ".");
    }
}
