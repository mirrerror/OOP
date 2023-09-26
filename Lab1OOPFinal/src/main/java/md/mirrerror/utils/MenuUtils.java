package md.mirrerror.utils;

public class MenuUtils {

    public static void sendMainMenu() {
        System.out.println("""
                Welcome to TUM's student management system!
                What do you want to do?
                
                g - General operations
                f - Faculty operations
                s - Student operations
                
                q - Quit program
                """);
    }

    public static void sendFacultyMenu() {
        System.out.println("""
                        Faculty operations
                        What do you want to do?
                        
                        ns/<faculty abbreviation>/<first name>/<last name>/<email>/<day>/<month>/<year> - create student
                        gs/<email> - graduate student
                        ds/<faculty abbreviation> - display enrolled students
                        dg/<faculty abbreviation> - display graduated students
                        bf/<faculty abbreviation>/<email> - check if student belongs to faculty
                        
                        b - Back
                        q - Quit Program
                        """);
    }

    public static void sendGeneralMenu() {
        System.out.println("""
                General operations
                What do you want to do?
                
                nf/<faculty name>/<faculty abbreviation>/<field> - create faculty
                ss/<student email> - search student and show faculty
                df - display faculties
                df/field - display all faculties of a field
                
                b - Back
                q - Quit Program
                """);
    }

    public static void sendStudentsMenu() {
        System.out.println("""
                Student operations
                What do you want to do?
                
                No options available at the moment.
                
                b - Back
                q - Quit Program
                """);
    }

}
