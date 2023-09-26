package md.mirrerror.data;

import md.mirrerror.entities.Faculty;
import md.mirrerror.entities.Student;
import md.mirrerror.entities.StudyField;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataRegistry {

    private static List<Faculty> faculties;

    public static void loadData() {
        faculties = new ArrayList<>();
        try {
            File file = new File("data.csv");
            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 3 || parts.length == 4) { // format: name,abbreviation,studyField,students
                    String name = parts[0].trim();
                    String abbreviation = parts[1].trim();
                    StudyField studyField = StudyField.match(parts[2].trim());
                    List<Student> students = new ArrayList<>();
                    if(parts.length == 4) {
                        String[] studentData = parts[3].trim().split(";");
                        for(String studentInfo : studentData) {
                            String[] studentParts = studentInfo.split("~");
                            if(studentParts.length == 6) { // format: firstName-lastName-email-enrollmentDate-dateOfBirth-hasGraduated
                                String firstName = studentParts[0].trim();
                                String lastName = studentParts[1].trim();
                                String email = studentParts[2].trim();
                                LocalDate enrollmentDate = LocalDate.parse(studentParts[3].trim());
                                LocalDate dateOfBirth = LocalDate.parse(studentParts[4].trim());
                                boolean hasGraduated = Boolean.parseBoolean(studentParts[5].trim());
                                students.add(new Student(firstName, lastName, email, enrollmentDate, dateOfBirth, hasGraduated));
                            }
                        }
                    }
                    faculties.add(new Faculty(name, abbreviation, students, studyField));
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveData() {
        try {
            File file = new File("data.csv");
            if(!file.exists()) file.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for(Faculty faculty : faculties) {
                StringBuilder facultyData = new StringBuilder();
                facultyData.append(faculty.getName()).append(",");
                facultyData.append(faculty.getAbbreviation()).append(",");
                facultyData.append(faculty.getStudyField().name()).append(",");
                List<Student> students = faculty.getStudents();
                for(int i = 0; i < students.size(); i++) {
                    Student student = students.get(i);
                    facultyData.append(student.getFirstName()).append("~")
                            .append(student.getLastName()).append("~")
                            .append(student.getEmail()).append("~")
                            .append(student.getEnrollmentDate()).append("~")
                            .append(student.getDateOfBirth()).append("~")
                            .append(student.hasGraduated());
                    if(i < students.size() - 1) facultyData.append(";");
                }
                bw.write(facultyData.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewFaculty(Faculty faculty) {
        faculties.add(faculty);
        saveData();
    }

    public void addStudentToFaculty(Student student, String facultyAbbreviation) {
        for(Faculty faculty : faculties) {
            if(faculty.getAbbreviation().equals(facultyAbbreviation)) {
                faculty.getStudents().add(student);
                break;
            }
        }
        saveData();
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
        saveData();
    }

    public static List<Faculty> getFaculties() {
        return faculties;
    }
}
