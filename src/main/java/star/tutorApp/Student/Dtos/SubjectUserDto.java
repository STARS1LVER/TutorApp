package star.tutorApp.Student.Dtos;

import java.time.LocalDateTime;

public class SubjectUserDto {

    private int id;
    private String tutorName;
    private String tutorEmail;
    private String subjectName;
    private String subjectDescription;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SubjectUserDto(int id, String tutorName, String tutorEmail, String subjectName, String subjectDescription, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.tutorName = tutorName;
        this.tutorEmail = tutorEmail;
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
