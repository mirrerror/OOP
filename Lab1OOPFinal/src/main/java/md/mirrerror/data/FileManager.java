package md.mirrerror.data;

import md.mirrerror.Main;
import md.mirrerror.entities.Faculty;
import md.mirrerror.entities.Student;
import md.mirrerror.entities.StudyField;
import md.mirrerror.utils.Pair;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private final List<Faculty> faculties;

    private final String FACULTIES_DATA_FILE = "faculties.csv";
    private final String STUDENTS_DATA_FILE = "students.csv";

    public FileManager(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public void loadData() {
        loadFaculties();
        loadStudents();
    }

    public void saveData() {
        saveFaculties();
        saveStudents();
    }

    public void loadFaculties() {
        faculties.clear();
        try {
            File file = new File(FACULTIES_DATA_FILE);
            if(createFileIfNeeded(file)) return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                Faculty faculty = parseFaculty(line);
                if (faculty != null) faculties.add(faculty);
            }
            br.close();
        } catch (IOException e) {
            Main.getLogger().error("Couldn't load the faculties from the file.");
            e.printStackTrace();
        }
    }

    public void saveFaculties() {
        try {
            File file = new File(FACULTIES_DATA_FILE);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            createFileIfNeeded(file);

            for (Faculty faculty : faculties) {
                String formattedFaculty = formatFaculty(faculty);
                if (formattedFaculty != null) {
                    bw.write(formattedFaculty);
                    bw.newLine();
                }
            }
            bw.close();
        } catch (IOException e) {
            Main.getLogger().error("Couldn't save the faculties to the file.");
            e.printStackTrace();
        }
    }

    public void loadStudents() {
        try {
            File file = new File(STUDENTS_DATA_FILE);
            if(createFileIfNeeded(file)) return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                Pair<Student, Integer> pair = parseStudent(line);
                if (pair != null) {
                    for(Faculty faculty : faculties) {
                        if(faculty.getId() == pair.getSecond()) {
                            faculty.getStudents().add(pair.getFirst());
                            break;
                        }
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            Main.getLogger().error("Couldn't load the students from the file.");
            e.printStackTrace();
        }
    }

    public void saveStudents() {
        try {
            File file = new File(STUDENTS_DATA_FILE);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            createFileIfNeeded(file);

            for(Faculty faculty : faculties) {
                for (Student student : faculty.getStudents()) {
                    String formattedStudent = formatStudent(student, faculty.getId());
                    if (formattedStudent != null) {
                        bw.write(formattedStudent);
                        bw.newLine();
                    }
                }
            }
            bw.close();
        } catch (IOException e) {
            Main.getLogger().error("Couldn't save the students to the file.");
            e.printStackTrace();
        }
    }

    private Faculty parseFaculty(String line) {
        String[] parts = line.split(",");
        if (parts.length >= 4) {
            int facultyId = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            String abbreviation = parts[2].trim();
            StudyField studyField = StudyField.valueOf(parts[3].trim());
            return new Faculty(facultyId, name, abbreviation, new ArrayList<>(), studyField);
        }
        return null;
    }

    private String formatFaculty(Faculty faculty) {
        return faculty.getId() + "," +
                faculty.getName() + "," +
                faculty.getAbbreviation() + "," +
                faculty.getStudyField().name();
    }

    private Pair<Student, Integer> parseStudent(String line) {
        String[] parts = line.split(",");
        if (parts.length == 7) {
            int facultyId = Integer.parseInt(parts[0].trim());
            String firstName = parts[1].trim();
            String lastName = parts[2].trim();
            String email = parts[3].trim();
            LocalDate enrollmentDate = LocalDate.parse(parts[4].trim());
            LocalDate dateOfBirth = LocalDate.parse(parts[5].trim());
            boolean hasGraduated = Boolean.parseBoolean(parts[6].trim());
            return new Pair<>(new Student(firstName, lastName, email, enrollmentDate, dateOfBirth, hasGraduated), facultyId);
        }
        return null;
    }

    private String formatStudent(Student student, int facultyId) {
        return String.format(
                "%d,%s,%s,%s,%s,%s,%b",
                facultyId,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getEnrollmentDate(),
                student.getDateOfBirth(),
                student.hasGraduated()
        );
    }

    private boolean createFileIfNeeded(File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
                Main.getLogger().info("Couldn't find the file \"" + file.getName() + "\". Created a new one instead.");
                return true;
            }
        } catch (IOException e) {
            Main.getLogger().error("Couldn't create the file \"" + file.getName() + "\".");
            e.printStackTrace();
        }
        return false;
    }
}
