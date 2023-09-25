package md.mirrerror;

import md.mirrerror.entities.Faculty;
import md.mirrerror.entities.Student;
import md.mirrerror.entities.StudyField;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataRegistry {

    private static List<Faculty> faculties;

    public void loadData() {
        faculties = new ArrayList<>();
        try {
            File file = new File("data.json");
            if (!file.exists()) {
                JSONArray emptyArray = new JSONArray();
                try (FileWriter emptyFileWriter = new FileWriter(file)) {
                    emptyArray.write(emptyFileWriter);
                }
            } else {
                try (FileReader fileReader = new FileReader(file)) {
                    JSONTokener tokener = new JSONTokener(fileReader);
                    JSONArray jsonArray = new JSONArray(tokener);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject facultyJson = jsonArray.getJSONObject(i);

                        String name = facultyJson.getString("name");
                        String abbreviation = facultyJson.getString("abbreviation");
                        StudyField studyField = StudyField.valueOf(facultyJson.getString("studyField"));

                        List<Student> students = new ArrayList<>();
                        JSONArray studentsArray = facultyJson.getJSONArray("students");
                        for (int j = 0; j < studentsArray.length(); j++) {
                            JSONObject studentJson = studentsArray.getJSONObject(j);
                            String firstName = studentJson.getString("firstName");
                            String lastName = studentJson.getString("lastName");
                            String email = studentJson.getString("email");
                            LocalDate enrollmentDate = LocalDate.parse(studentJson.getString("enrollmentDate"));
                            LocalDate dateOfBirth = LocalDate.parse(studentJson.getString("dateOfBirth"));
                            boolean hasGraduated = studentJson.getBoolean("hasGraduated");

                            students.add(new Student(firstName, lastName, email, enrollmentDate, dateOfBirth, hasGraduated));
                        }

                        faculties.add(new Faculty(name, abbreviation, students, studyField));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        JSONArray jsonArray = new JSONArray();
        for (Faculty faculty : faculties) {
            JSONObject facultyJson = new JSONObject();
            facultyJson.put("name", faculty.getName());
            facultyJson.put("abbreviation", faculty.getAbbreviation());
            facultyJson.put("studyField", faculty.getStudyField().toString());

            JSONArray studentsArray = new JSONArray();
            for (Student student : faculty.getStudents()) {
                JSONObject studentJson = new JSONObject();
                studentJson.put("firstName", student.getFirstName());
                studentJson.put("lastName", student.getLastName());
                studentJson.put("email", student.getEmail());
                studentJson.put("enrollmentDate", student.getEnrollmentDate().toString());
                studentJson.put("dateOfBirth", student.getDateOfBirth().toString());
                studentJson.put("hasGraduated", student.hasGraduated());
                studentsArray.put(studentJson);
            }
            facultyJson.put("students", studentsArray);
            jsonArray.put(facultyJson);
        }

        try (FileWriter fileWriter = new FileWriter("data.json")) {
            jsonArray.write(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addNewFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

    public void addStudentToFaculty(Student student, String facultyAbbreviation) {
        for(Faculty faculty : faculties) {
            if(faculty.getAbbreviation().equals(facultyAbbreviation)) {
                faculty.getStudents().add(student);
                break;
            }
        }
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
    }

    public static List<Faculty> getFaculties() {
        return faculties;
    }
}
