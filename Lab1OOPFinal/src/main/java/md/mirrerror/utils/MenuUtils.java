package md.mirrerror.utils;

public class MenuUtils {

    public static void sendMainMenuHelpMessage() {
        System.out.println("""
                Welcome to TUM's student management system!
                What do you want to do?
                
                g - General operations
                f - Faculty operations
                s - Student operations
                
                q - Quit program
                """);
    }

    public static void sendFacultyMenuHelpMessage() {
        System.out.println("""
                        Faculty operations
                        What do you want to do?
                        
                        ns/<faculty abbreviation>/<first name>/<last name>/<email>/<day>/<month>/<year> - create student
                        gs/<email> - graduate student
                        ds/<faculty abbreviation> - display enrolled students
                        dg/<faculty abbreviation> - display graduated students
                        bf/<faculty abbreviation>/<email> - check if student belongs to faculty
                        bu/<file name> - perform a batch update
                        
                        b - Back
                        q - Quit Program
                        """);
    }

    public static void sendGeneralMenuHelpMessage() {
        System.out.println("""
                General operations
                What do you want to do?
                
                nf/<faculty name>/<faculty abbreviation>/<field> - create faculty
                ss/<student email> - search student and show faculty
                df - display faculties
                df/field - display all faculties of a field
                bu/<file name> - perform a batch update
                
                b - Back
                q - Quit Program
                """);
    }

    public static void sendStudentsMenuHelpMessage() {
        System.out.println("""
                Student operations
                What do you want to do?
                
                bu/<file name> - perform a batch update
                
                b - Back
                q - Quit Program
                """);
    }

}
