package star.tutorApp.Student.Dtos;

import java.util.List;

public class TutorDto {
    private String name;
    private String email;
    private List<SubjectWithTutorsDto> subjects;

    public TutorDto() { }

    // Constructor con todos los campos (opcional, pero útil)
    public TutorDto(String name, String email, List<SubjectWithTutorsDto> subjects) {
        this.name = name;
        this.email = email;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SubjectWithTutorsDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectWithTutorsDto> subjects) {
        this.subjects = subjects;
    }
}
