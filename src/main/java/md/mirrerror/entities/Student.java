package md.mirrerror.entities;

import java.time.LocalDate;

public class Student {

    private String firstName, lastName, email;
    private LocalDate enrollmentDate, dateOfBirth;
    private boolean hasGraduated;

    public Student(String firstName, String lastName, String email, LocalDate enrollmentDate, LocalDate dateOfBirth, boolean hasGraduated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.dateOfBirth = dateOfBirth;
        this.hasGraduated = hasGraduated;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean hasGraduated() {
        return hasGraduated;
    }

    public void setGraduated(boolean hasGraduated) {
        this.hasGraduated = hasGraduated;
    }
}
