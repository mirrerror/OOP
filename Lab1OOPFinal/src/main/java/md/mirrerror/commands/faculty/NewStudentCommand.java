package md.mirrerror.commands.faculty;

import md.mirrerror.AppState;
import md.mirrerror.Main;
import md.mirrerror.commands.Command;
import md.mirrerror.entities.Faculty;
import md.mirrerror.entities.Student;

import java.time.DateTimeException;
import java.time.LocalDate;

public class NewStudentCommand extends Command {
    public NewStudentCommand() {
        super("ns");
    }

    @Override
    public void onCommand(String[] args) {
        if(Main.getAppState() != AppState.FACULTY_OPERATIONS) {
            System.out.println("Switch to the faculty operations branch first.");
            return;
        }

        if(args.length < 7) {
            System.out.println("Usage: ns/<faculty abbreviation>/<first name>/<last name>/<email>/<day>/<month>/<year> - create student");
            return;
        }

        int year, month, day;

        try {
            year = Integer.parseInt(args[6]);
            month = Integer.parseInt(args[5]);
            day = Integer.parseInt(args[4]);
        } catch (NumberFormatException ignored) {
            System.out.println("You have specified a wrong date.");
            return;
        }

        Student student;
        try {
            student = new Student(args[1], args[2], args[3], LocalDate.now(), LocalDate.of(year, month, day), false);
        } catch (DateTimeException ignored) {
            System.out.println("You have specified a wrong date.");
            return;
        }

        Faculty faculty = Main.getDataRegistry().searchFacultyByAbbreviation(args[0]);
        if(faculty == null) {
            System.out.println("Faculty with this abbreviation doesn't exist.");
            return;
        }

        Main.getDataRegistry().addStudentToFaculty(student, args[0]);
        System.out.println("Successfully added a new student to the faculty " + faculty.getName() + ".");
    }
}
