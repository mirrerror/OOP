package md.mirrerror.entities;

import java.util.ArrayList;
import java.util.List;

public class Faculty {

    private final int id;
    private String name, abbreviation;
    private List<Student> students;
    private StudyField studyField;

    public Faculty(int id, String name, String abbreviation, List<Student> students, StudyField studyField) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.students = students;
        this.studyField = studyField;
    }

    public Faculty(int id, String name, String abbreviation, StudyField studyField) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.students = new ArrayList<>();
        this.studyField = studyField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public StudyField getStudyField() {
        return studyField;
    }

    public void setStudyField(StudyField studyField) {
        this.studyField = studyField;
    }

    public int getId() {
        return id;
    }
}
