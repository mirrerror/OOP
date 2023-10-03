package md.mirrerror.data;

import md.mirrerror.Main;
import md.mirrerror.entities.Faculty;
import md.mirrerror.entities.Student;
import md.mirrerror.entities.StudyField;

import java.util.*;

public class DataRegistry {

    private static List<Faculty> faculties = new ArrayList<>();

    public void addNewFaculty(Faculty faculty) {
        faculties.add(faculty);
        Main.getFileManager().saveData();
    }

    public void addStudentToFaculty(Student student, String facultyAbbreviation) {
        for(Faculty faculty : faculties) {
            if(faculty.getAbbreviation().equals(facultyAbbreviation)) {
                faculty.getStudents().add(student);
                break;
            }
        }
        Main.getFileManager().saveData();
    }

    public Student searchStudent(String email) {
        for(Faculty faculty : faculties) {
            for(Student student : faculty.getStudents()) {
                if(student.getEmail().equals(email)) return student;
            }
        }
        return null;
    }

    public Faculty searchFacultyByStudentEmail(String studentEmail) {
        for(Faculty faculty : faculties) {
            for(Student student : faculty.getStudents()) {
                if(student.getEmail().equals(studentEmail)) return faculty;
            }
        }
        return null;
    }

    public Faculty searchFacultyByName(String name) {
        for(Faculty faculty : faculties) {
            if(faculty.getName().equalsIgnoreCase(name)) return faculty;
        }
        return null;
    }

    public Faculty searchFacultyByAbbreviation(String facultyAbbreviation) {
        for(Faculty faculty : faculties) if(faculty.getAbbreviation().equalsIgnoreCase(facultyAbbreviation)) return faculty;
        return null;
    }

    public List<Student> getGraduatedStudents(Faculty faculty) {
        List<Student> students = new ArrayList<>();
        for(Student student : faculty.getStudents()) if(student.hasGraduated()) students.add(student);
        return students;
    }

    public void displayGraduatedStudents(Faculty faculty) {
        List<Student> students = getGraduatedStudents(faculty);
        if(students.isEmpty()) {
            System.out.println("There are no graduated students yet for the specified faculty.");
            return;
        }
        int index = 1;
        System.out.println("Graduated students from " + faculty.getName() + " (" + faculty.getAbbreviation() + "):");
        for(Student student : students) {
            System.out.println(index + ". Full name: " + student.getFirstName() + " " + student.getLastName() + ", email: " + student.getEmail() + ", date of birth: " + student.getDateOfBirth() + ", enrollment date: " + student.getEnrollmentDate());
            index += 1;
        }
    }

    public void displayStudents(Faculty faculty) {
        if(faculty.getStudents().isEmpty()) {
            System.out.println("There are no students yet for the specified faculty.");
            return;
        }
        int index = 1;
        System.out.println("Students from " + faculty.getName() + " (" + faculty.getAbbreviation() + "):");
        for(Student student : faculty.getStudents()) {
            System.out.println(index + ". Full name: " + student.getFirstName() + " " + student.getLastName() + ", email: " + student.getEmail() + ", date of birth: " + student.getDateOfBirth() + ", enrollment date: " + student.getEnrollmentDate() + ", graduated: " + (student.hasGraduated() ? "yes" : "no") + ".");
            index += 1;
        }
    }

    public void displayFaculties() {
        if(faculties.isEmpty()) {
            System.out.println("There are no faculties yet.");
            return;
        }
        int index = 1;
        System.out.println("Registered faculties:");
        for(Faculty faculty : faculties) {
            System.out.println(index + ". " + faculty.getName() + " (" + faculty.getAbbreviation() + "). Field: " + faculty.getStudyField() + ", students: " + faculty.getStudents().size() + ".");
            index += 1;
        }
    }

    public void displayFaculties(StudyField studyField) {
        List<String> facultyList = new ArrayList<>();
        int index = 1;
        for(Faculty faculty : faculties) {
            if(faculty.getStudyField() != studyField) continue;
            facultyList.add(index + ". " + faculty.getName() + " (" + faculty.getAbbreviation() + "). Students: " + faculty.getStudents().size() + ".");
            index += 1;
        }

        if(facultyList.isEmpty()) {
            System.out.println("There are no faculties with the specified study field yet.");
            return;
        }

        System.out.println("Registered faculties with study field " + studyField + ":");
        for(String s : facultyList) System.out.println(s);
    }

    public void graduateStudent(String email) {
        for(Faculty faculty : faculties) {
            for(Student student : faculty.getStudents()) {
                if(student.getEmail().equals(email)) {
                    student.setGraduated(true);
                    break;
                }
            }
        }
        Main.getFileManager().saveData();
    }

    public int findFirstAvailableId() {
        Set<Integer> ids = new TreeSet<>();
        for(Faculty faculty : faculties) ids.add(faculty.getId());
        int prevId = -1;
        for(int id : ids) {
            if(prevId == -1) {
                prevId = id;
                continue;
            }
            if(id - prevId > 1) return prevId + 1;
        }
        return ids.size();
    }

    public static List<Faculty> getFaculties() {
        return faculties;
    }
}
