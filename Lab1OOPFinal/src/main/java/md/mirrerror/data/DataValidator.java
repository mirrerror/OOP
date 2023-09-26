package md.mirrerror.data;

import md.mirrerror.entities.Faculty;
import md.mirrerror.entities.Student;
import md.mirrerror.entities.StudyField;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;

public class DataValidator {

    public static boolean validateDate(String year, String month, String day) {
        try {
            LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            return true;
        } catch (DateTimeException | NumberFormatException ignored) {
            System.out.println("You have specified a wrong date.");
            return false;
        }
    }

    public static boolean validateFaculty(Faculty faculty, boolean mustExist) {
        if(mustExist) {
            if(faculty == null) {
                System.out.println("The specified faculty doesn't exist.");
                return false;
            }
        } else {
            if(faculty != null) {
                System.out.println("The specified faculty already exists.");
                return false;
            }
        }
        return true;
    }

    public static boolean validateStudent(Student student, boolean mustExist) {
        if(mustExist) {
            if(student == null) {
                System.out.println("The specified student doesn't exist.");
                return false;
            }
        } else {
            if(student != null) {
                System.out.println("The specified student already exists.");
                return false;
            }
        }
        return true;
    }

    public static boolean validateStudyField(String rawStudyField) {
        try {
            StudyField.match(rawStudyField);
            return true;
        } catch (IllegalArgumentException ignored) {
            String studyFields = Arrays.toString(StudyField.values());
            System.out.println("Invalid faculty name. Available ones are: " + studyFields.substring(1, studyFields.length() - 1) + ".");
            return false;
        }
    }

    public static boolean validateEmailString(String email) {
        if(email.contains("~")) {
            System.out.println("An email can't contain the \"~\" characters.");
            return false;
        }
        return true;
    }

}
